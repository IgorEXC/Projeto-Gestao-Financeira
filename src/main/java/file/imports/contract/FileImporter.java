package file.imports.contract;

import com.financas.gestaofinanceira.domain.dto.UserResponseDTO;

import java.io.InputStream;
import java.util.List;

public interface FileImporter {

    List<UserResponseDTO> importFile(InputStream inputStream) throws Exception;
}
