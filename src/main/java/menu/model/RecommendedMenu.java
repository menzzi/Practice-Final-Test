package menu.model;

import java.util.List;

public class RecommendedMenu {
    private final String coach;
    private List<String> recommendedMenus;

    public RecommendedMenu(String coach, List<String> recommendedMenus){
        this.coach = coach;
        this.recommendedMenus = recommendedMenus;
    }

    public void updateMenu(String menu){
        if(recommendedMenus.contains(menu)){
            throw new IllegalArgumentException();
        }
        recommendedMenus.add(menu);
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(coach);
        for(String recommendedMenu : recommendedMenus){
            stringBuilder.append(" | ");
            stringBuilder.append(recommendedMenu);
        }
        return stringBuilder.toString();
    }
}
