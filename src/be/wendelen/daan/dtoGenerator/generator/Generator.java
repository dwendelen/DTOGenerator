package be.wendelen.daan.dtoGenerator.generator;

import be.wendelen.daan.dtoGenerator.model.ast.Field;
import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.model.dto.DTO;
import be.wendelen.daan.dtoGenerator.model.dto.Mapper;
import be.wendelen.daan.dtoGenerator.model.dto.MapperTest;
import be.wendelen.daan.dtoGenerator.model.psi.ClassFromPsiFactory;
import be.wendelen.daan.dtoGenerator.model.psi.FieldFromPsiFactory;
import be.wendelen.daan.dtoGenerator.renderer.Renderer;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;

import java.util.List;

public class Generator {
    private Renderer renderer = new Renderer();
    private FieldFromPsiFactory fieldFromPsiFactory = new FieldFromPsiFactory();
    private ClassFromPsiFactory classFromPsiFactory = new ClassFromPsiFactory();

    public void generate(final GenerationConfiguration generationConfiguration) {
        new WriteCommandAction.Simple(generationConfiguration.from.getProject(), "Generate DTO") {
            public void run() {
                List<Field> fields = fieldFromPsiFactory.createList(generationConfiguration.fields);
                DTO dto = new DTO(generationConfiguration.dtoClassName, fields);

                addClass(dto, generationConfiguration.dtoPackage);

                Class from = classFromPsiFactory.create(generationConfiguration.from);
                Mapper mapper = new Mapper(generationConfiguration.mapperClassName, from, dto);

                addClass(mapper, generationConfiguration.mapperPackage);

                MapperTest mapperTest = new MapperTest(generationConfiguration.mapperTestName, mapper);

                addClass(mapperTest, generationConfiguration.mapperPackage);
            }
        }.execute();
    }

    private void addClass(Class aClass, final PsiDirectory psiPackage) {
        String code = renderer.renderClass(aClass);

        PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(psiPackage.getProject());
        PsiClass newClass = elementFactory.createClassFromText(code, psiPackage);

        PsiClass attachedClass = (PsiClass) psiPackage.add(newClass.getInnerClasses()[0]);
        JavaCodeStyleManager.getInstance(psiPackage.getProject()).shortenClassReferences(attachedClass);
    }
}
