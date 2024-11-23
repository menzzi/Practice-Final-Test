package menu.model;

import java.util.List;

public class RecommendedMenus {
    private final String coach;
    private final List<String> recommendedMenus;

    public RecommendedMenus(String coach, List<String> recommendedMenus){
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
