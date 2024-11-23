package menu.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.model.TheMenu;
import menu.model.RecommendedCategories;
import menu.model.RecommendedMenus;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendationController {
    private final InputView input;
    private final OutputView output;

    private final List<String> dayOfTheWeek = new ArrayList<>(Arrays.asList("월요일", "화요일", "수요일", "목요일", "금요일"));
    private final List<RecommendedMenus> recommendedMenus = new ArrayList<>();
    private final Map<String, RecommendedMenus> result = new LinkedHashMap<>();

    public RecommendationController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        output.printServiceLaunchStatement();
        List<String> coachNames = inputCoachNames();
        RecommendedCategories recommendedCategories = recommendCategory();
        recommendMenu(recommendedCategories.getRecommendedCategories(), coachNames);
        printResult(recommendedCategories);
    }

    private RecommendedCategories recommendCategory() {
        RecommendedCategories recommendedCategories = new RecommendedCategories(new ArrayList<>());
        for (String day : dayOfTheWeek) {
            repeatRecommendCategory(recommendedCategories);
        }
        return recommendedCategories;
    }

    private void repeatRecommendCategory(RecommendedCategories recommendedCategories) {
        while (true) {
            String category = TheMenu.recommendCategory(Randoms.pickNumberInRange(1, 5));
            try {
                recommendedCategories.updateCategories(category);
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    private void recommendMenu(List<String> recommendedCategories, List<String> coachNames) {
        for (String coach : coachNames) {
            RecommendedMenus eachCoachMenus = updateHateMenu(coach);
            result.put(coach, eachCoachMenus);
        }
        for (String category : recommendedCategories) {
            for (String name : result.keySet()) {
                RecommendedMenus eachCoachMenus = result.get(name);
                updateMenu(eachCoachMenus, category);
            }
        }

    }

    private void updateMenu(RecommendedMenus eachCoachMenus, String category) {
        while (true) {
            try {
                eachCoachMenus.updateMenu(TheMenu.recommendMenu(category));
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    private RecommendedMenus updateHateMenu(String coach) {
        boolean isCollectInput = false;
        List<String> hateMenus = new ArrayList<>();
        while (!isCollectInput) {
            try {
                hateMenus = input.inputHateMenu(coach);
                // 없는 메뉴인지 확인
                isCollectInput = true;
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
        return new RecommendedMenus(coach, hateMenus, new ArrayList<>());
    }

    private List<String> inputCoachNames() {
        while (true) {
            try {
                return input.inputCoachNames();
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printResult(RecommendedCategories recommendedCategories) {
        output.printResult(dayOfTheWeek);
        output.printMenuRecommendationResult(recommendedCategories.toString());
        for (String name : result.keySet()) {
            output.printMenuRecommendationResult(result.get(name).toString());
        }
        output.printEndMessage();
    }
}
