package oncall.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidationTest {

    @ParameterizedTest
    @CsvSource({
            "5-월",
            "5, 월"
    })
    void 콤마를_제외한_기호를_입력하면_예외가_발생한다(String input) {
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.validateInputFormat(input);
        });
    }
}
