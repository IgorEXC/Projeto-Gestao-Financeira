package file.imports.impl;

import com.financas.gestaofinanceira.domain.dto.UserResponseDTO;
import file.imports.contract.FileImporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvImporter implements FileImporter {

    @Override
    public List<UserResponseDTO> importFile(InputStream inputStream) throws Exception {
        CSVFormat format = CSVFormat.Builder.create()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));
        return parseRecordsToUserResponseDTO(records);
    }

    private List<UserResponseDTO> parseRecordsToUserResponseDTO(Iterable<CSVRecord> records) {
        List<UserResponseDTO> users = new ArrayList<>();

        for (CSVRecord record : records) {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setName(record.get("name"));
            dto.setCpf(record.get("cpf"));
            dto.setUsername(record.get("username"));
            dto.setEmail(record.get("email"));
            dto.setMonthlyIncome(Double.valueOf(record.get("monthly_income")));
            users.add(dto);
        }

        return users;
    }


}
