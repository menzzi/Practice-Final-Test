package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TheMenu {
    DEFAULT("", new ArrayList<>(Arrays.asList(("").split(",")))),
    JAPANESE("일식", new ArrayList<>(Arrays.asList(("규동,우동,미소시루,스시,가츠동,오니기리,하이라이스,라멘,오코노미야끼").split(",")))),
    KOREAN("한식", new ArrayList<>(Arrays.asList(("김밥,김치찌개,쌈밥,된장찌개,비빔밥,칼국수,불고기,떡볶이,제육볶음").split(",")))),
    CHINESE("중식", new ArrayList<>(Arrays.asList(("깐풍기,볶음면,동파육,짜장면,짬뽕,마파두부,탕수육,토마토 달걀볶음,고추잡채").split(",")))),
    ASIAN("아시안", new ArrayList<>(Arrays.asList(("팟타이,카오 팟,나시고렝,파인애플 볶음밥,쌀국수,똠얌꿍,반미,월남쌈,분짜").split(",")))),
    WESTERN("양식", new ArrayList<>(Arrays.asList(("라자냐,그라탱,뇨끼,끼슈,프렌치 토스트,바게트,스파게티,피자,파니니").split(","))));

    private final String category;
    private final List<String> recommendationMenus;

    TheMenu(String category, List<String> recommendationMenus) {
        this.category = category;
        this.recommendationMenus = recommendationMenus;
    }

    public static String recommendCategory(int number) {
        return TheMenu.values()[number].category;
    }

    public static String recommendMenu(String categoryName) {
        TheMenu menu = findCategory(categoryName);
        assert menu != null;
        return Randoms.shuffle(menu.recommendationMenus).get(0);
    }

    private static TheMenu findCategory(String categoryName) {
        for (TheMenu menu : TheMenu.values()) {
            if (menu.category.equals(categoryName)) {
                return menu;
            }
        }
        return null;
    }
}
