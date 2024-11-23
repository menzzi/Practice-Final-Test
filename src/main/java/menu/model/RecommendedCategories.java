package menu.model;

import java.util.List;

public class RecommendedCategories {
    private final List<String> recommendedCategories;

    public RecommendedCategories(List<String> recommendedCategories){
        this.recommendedCategories = recommendedCategories;
    }

    public void updateCategories(String categoryName){
        int duplicatedCount = 0;
        for(String category : recommendedCategories){
            if(category.equals(categoryName)){
                duplicatedCount++;
            }
        }
        if(duplicatedCount > 2){
            throw new IllegalArgumentException();
        }
        recommendedCategories.add(categoryName);
    }

    public List<String> getRecommendedCategories() {
        return recommendedCategories;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("카테고리");
        for(String category : recommendedCategories){
            stringBuilder.append(" | ");
            stringBuilder.append(category);
        }
        return stringBuilder.toString();
    }
}
