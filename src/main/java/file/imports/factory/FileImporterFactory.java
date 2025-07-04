package file.imports.factory;

import file.imports.contract.FileImporter;
import file.imports.impl.CsvImporter;
import file.imports.impl.XlsxImporter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FileImporterFactory {

    private Logger log = LoggerFactory.getLogger(FileImporterFactory.class);

    private final ApplicationContext context; //importe do springframework

    public FileImporter getFileImporter(String fileName) throws RuntimeException{
        if(fileName.endsWith(".xlsx")){
            return context.getBean(XlsxImporter.class);
            //linha acima é como instanciar uma classe. Equivalente a return new XlsxImporter;
        }else if(fileName.endsWith(".csv")){
            return context.getBean(CsvImporter.class);
        }else{
            throw new RuntimeException("Tipo de arquivo não suportado: " + fileName + ".");
        }
    }
}
