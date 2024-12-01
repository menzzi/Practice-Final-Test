package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {
    private final String NAME_INPUT_GUIDE = "코치의 이름을 입력해 주세요. (, 로 구분)";
    private final String MENU_INPUT_GUIDE = "%s(이)가 못 먹는 메뉴를 입력해 주세요.";
    private final String INVALIDATE_COACH_COUNT = "코치는 최소 2명 이상, 최대 5명 이하로 입력해야 합니다.";
    private final String INVALIDATE_DUPLICATE_NAME = "코치의 이름이 중복되면 안됩니다.";
    private final String INVALIDATE_NAME_LENGTH = "이름은 최소 2글자 이상, 최대 4글자 이하로 입력해야 합니다.";
    private final String INVALIDATE_MENU_COUNT = "메뉴는 최대 2개로 입력해야 합니다.";

    public List<String> inputCoachNames() {
        System.out.println(NAME_INPUT_GUIDE);
        return validateCoachNames(Console.readLine());
    }

    public List<String> inputHateMenu(String coachName) {
        System.out.printf(MENU_INPUT_GUIDE, coachName);
        System.out.println();
        return validateHateMenus(Console.readLine());
    }

    private List<String> validateCoachNames(String userInput) {
        List<String> coachNames = new ArrayList<>(Arrays.asList(userInput.split(",")));
        if (coachNames.size() < 2 || coachNames.size() > 5) {
            throw new IllegalArgumentException(INVALIDATE_COACH_COUNT);
        }
        for (String name : coachNames) {
            validateCoachNameLength(name);
        }
        validateCoachNameDuplicate(coachNames);
        return coachNames;
    }

    private void validateCoachNameLength(String name) {
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException(INVALIDATE_NAME_LENGTH);
        }
    }

    private void validateCoachNameDuplicate(List<String> coachNames) {
        Set<String> duplicateCheck = new HashSet<>(coachNames);
        if (duplicateCheck.size() != coachNames.size()) {
            throw new IllegalArgumentException(INVALIDATE_DUPLICATE_NAME);
        }
    }

    private List<String> validateHateMenus(String userInput) {
        List<String> HateMenus = new ArrayList<>(Arrays.asList(userInput.split(",")));
        if (HateMenus.size() > 2) {
            throw new IllegalArgumentException(INVALIDATE_MENU_COUNT);
        }
        return HateMenus;
    }
}
