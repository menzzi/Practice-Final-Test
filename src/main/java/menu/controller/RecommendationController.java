package menu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import menu.model.Menu;
import menu.model.RecommendedCategories;
import menu.model.RecommendedMenus;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendationController {
    private final InputView input;
    private final OutputView output;

    private final List<String> dayOfTheWeek = new ArrayList<>(Arrays.asList("월요일", "화요일", "수요일", "목요일", "금요일"));
    private final List<RecommendedMenus> recommendedMenus = new ArrayList<>();

    public RecommendationController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run(){
        output.printServiceLaunchStatement();
        List<String> coachNames = inputCoachNames();
        RecommendedCategories recommendedCategories = recommendCategory();
        recommendMenu(recommendedCategories.getRecommendedCategories(),coachNames);
        printResult(recommendedCategories);
    }

    private RecommendedCategories recommendCategory(){
        RecommendedCategories recommendedCategories = new RecommendedCategories(new ArrayList<>());
        for (String day : dayOfTheWeek) {
            repeatRecommendCategory(recommendedCategories);
        }
        return recommendedCategories;
    }

    private void repeatRecommendCategory(RecommendedCategories recommendedCategories){
        while (true) {
            String category = Menu.recommendCategory();
            try {
                recommendedCategories.updateCategories(category);
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    private void recommendMenu(List<String> recommendedCategories, List<String> coachNames){
        for(String coach : coachNames){
            RecommendedMenus eachCoachMenus = updateHateMenu(coach);
            for(String category : recommendedCategories){
                Menu menu = Menu.getCategory(category);
                updateMenu(eachCoachMenus,menu);
            }
            recommendedMenus.add(eachCoachMenus);
        }
    }

    private void updateMenu(RecommendedMenus eachCoachMenus, Menu menu){
        while (true) {
            try {
                eachCoachMenus.updateMenu(menu.recommendMenu());
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    private RecommendedMenus updateHateMenu(String coach){
        boolean isCollectInput = false;
        List<String> hateMenus = new ArrayList<>();
        while(!isCollectInput){
            try{
                hateMenus = input.inputHateMenu(coach);
                // 없는 메뉴인지 확인
                isCollectInput = true;
            }catch (IllegalArgumentException e){
                output.printErrorMessage(e.getMessage());
            }
        }
        return new RecommendedMenus(coach, hateMenus, new ArrayList<>());
    }

    private List<String> inputCoachNames(){
        while (true) {
            try {
                return input.inputCoachNames();
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printResult(RecommendedCategories recommendedCategories){
        output.printResult(dayOfTheWeek);
        output.printMenuRecommendationResult(recommendedCategories.toString());
        for(RecommendedMenus recommendedMenu: recommendedMenus){
            output.printMenuRecommendationResult(recommendedMenu.toString());
        }
        output.printEndMessage();
    }
}
