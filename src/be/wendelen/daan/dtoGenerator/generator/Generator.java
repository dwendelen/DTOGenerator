package be.wendelen.daan.dtoGenerator.generator;

import be.wendelen.daan.dtoGenerator.model.ast.Field;
import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.model.ast.Method;
import be.wendelen.daan.dtoGenerator.model.dto.DTO;
import be.wendelen.daan.dtoGenerator.model.dto.Mapper;
import be.wendelen.daan.dtoGenerator.model.dto.MapperTest;
import be.wendelen.daan.dtoGenerator.model.psi.ClassFromPsiFactory;
import be.wendelen.daan.dtoGenerator.model.psi.FieldFromPsiFactory;
import be.wendelen.daan.dtoGenerator.model.psi.MethodFromPsiFactory;
import be.wendelen.daan.dtoGenerator.renderer.Renderer;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.ide.util.PackageUtil;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.tokenindex.Tokenizer;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Generator {
    private Renderer renderer = new Renderer();
    private MethodFromPsiFactory methodFromPsiFactory = new MethodFromPsiFactory();
    private ClassFromPsiFactory classFromPsiFactory = new ClassFromPsiFactory();

    public void generate(final GenerationConfiguration generationConfiguration) {
        new WriteCommandAction.Simple(generationConfiguration.from.getProject(), "Generate DTO") {
            public void run() {
                List<Method> fields = methodFromPsiFactory.createList(generationConfiguration.methods);

                DTO dto = new DTO(
                        generationConfiguration.dtoClassName,
                        generationConfiguration.dtoPackage.getQualifiedName(),
                        fields);
                addClass(dto, generationConfiguration.dtoPackage, false, generationConfiguration);

                Class from = classFromPsiFactory.create(generationConfiguration.from);
                Mapper mapper = new Mapper(
                        generationConfiguration.mapperClassName,
                        generationConfiguration.mapperPackage.getQualifiedName(),
                        from, dto);
                addClass(mapper, generationConfiguration.mapperPackage, false, generationConfiguration);

                if(generationConfiguration.generateMapperTest) {
                    MapperTest mapperTest = new MapperTest(
                            generationConfiguration.mapperTestName,
                            generationConfiguration.mapperPackage.getQualifiedName(),
                            mapper);
                    addClass(mapperTest, generationConfiguration.mapperPackage, true, generationConfiguration);
                }
            }
        }.execute();
    }

    private void addClass(Class aClass, final PsiPackage psiPackage, boolean inTest, GenerationConfiguration config) {
        String code = renderer.renderClass(aClass);
        PsiDirectory directory = getDirectoryFromPackage(psiPackage, inTest, config);

        PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(psiPackage.getProject());
        PsiClass newClass = elementFactory.createClassFromText(code, directory);

        PsiClass attachedClass = (PsiClass) directory.add(newClass.getInnerClasses()[0]);
        JavaCodeStyleManager.getInstance(psiPackage.getProject()).shortenClassReferences(attachedClass);
    }

    private PsiDirectory getDirectoryFromPackage(PsiPackage psiPackage, boolean inTest, GenerationConfiguration config) {

        if (inTest) {
            return returnOrCreateDirectory(config.testRoot, psiPackage.getQualifiedName());
        } else {
            return returnOrCreateDirectory(config.sourceRoot, psiPackage.getQualifiedName());
        }
    }

    private PsiDirectory returnOrCreateDirectory(PsiDirectory base, String packageName) {
        StringTokenizer tokenizer = new StringTokenizer(packageName, ".");
        while (tokenizer.hasMoreTokens()) {
            base = PackageUtil.findOrCreateSubdirectory(base, tokenizer.nextToken());
        }
        return  base;
    }


    private String getLongName(String className, PsiPackage psiPackage) {
        return psiPackage.getQualifiedName() + "." + className;
    }
}
