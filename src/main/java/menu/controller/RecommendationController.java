package menu.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import menu.model.Menu;
import menu.model.RecommendedCategories;
import menu.model.RecommendedMenus;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendationController {
    private final InputView input;
    private final OutputView output;

    private final List<String> dayOfTheWeek = Arrays.asList(("월요일,화요일,수요일,목요일,금요일").split(","));

    public RecommendationController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run(){
        output.printServiceLaunchStatement();
        List<String> coachNames = inputCoachNames();
        RecommendedCategories recommendedCategories = recommendCategory();
        recommendMenu(recommendedCategories.getRecommendedCategories(),coachNames);
    }

    private RecommendedCategories recommendCategory(){
        RecommendedCategories recommendedCategories = new RecommendedCategories(Collections.emptyList());
        int index = 0;
        while(index < dayOfTheWeek.size()){
            String category = Menu.recommendCategory();
            try{
                recommendedCategories.updateCategories(category);
                index ++;
            }catch (IllegalArgumentException ignored){
            }
        }
        return recommendedCategories;
    }

    private void recommendMenu(List<String> recommendedCategories, List<String> coachNames){
        for(String coach : coachNames){
            RecommendedMenus recommendedMenu = updateHateMenu(coach);
            for(String category : recommendedCategories){
                Menu menu = Menu.getCategory(category);
                updateMenu(recommendedMenu,menu);
            }
        }
    }

    private void updateMenu(RecommendedMenus recommendedMenu, Menu menu){
        try{
            recommendedMenu.updateMenu(menu.recommendMenu());
        }catch (IllegalArgumentException e){
            updateMenu(recommendedMenu, menu);
        }
    }

    private RecommendedMenus updateHateMenu(String coach){
        boolean isCollectInput = false;
        List<String> hateMenus = List.of();
        while(!isCollectInput){
            try{
                hateMenus = input.inputHateMenu(coach);
                // 없는 메뉴인지 확인
                isCollectInput = true;
            }catch (IllegalArgumentException e){
                output.printErrorMessage(e.getMessage());
            }
        }
        return new RecommendedMenus(coach, hateMenus, Collections.emptyList());
    }

    private List<String> inputCoachNames(){
        try{
            return input.inputCoachNames();
        }catch (IllegalArgumentException e){
            output.printErrorMessage(e.getMessage());
            return inputCoachNames();
        }
    }

    private void printResult(){
        
    }
}
