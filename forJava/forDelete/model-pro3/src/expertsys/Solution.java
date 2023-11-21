package expertsys;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月19日 16:14
 * 项目名称:  forDelete
 * 文件名称:  testMain
 * 文件描述:  @Description:
 */
public class Solution {
    String[] features = {"有毛", "产奶", "有羽毛", "会飞", "会下蛋", "吃肉", "有犬齿", "有爪", "眼睛盯前方", "有蹄",
            "反刍", "黄褐色", "有斑点", "有黑色条纹", "长脖", "长腿", "不会飞", "会游泳", "黑白两色", "善飞"};
    private String input = "";
    private int[] conditions;
    private RuleRepository ruleRepository;
    public void AnimalIdentificationExpertSystem() {

        Scanner scanner = new Scanner(System.in);
        int len = 0;
        len = scanner.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        ruleRepository = new RuleRepository(arr);

        String res = ruleRepository.reasoning();
        String animalString = ruleRepository.getAnimals();
        System.out.println("res = " + res);
        System.out.println("animalString = " + animalString);
    }
}
