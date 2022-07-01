package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void Main(String[] args)
    {   Scanner scanner = new Scanner(System.in);
        int reviewsRepositoryCount = Integer.parseInt(scanner.nextLine());

        List<String> reviewsRepository = new ArrayList<String>();

        for (int i = 0; i < reviewsRepositoryCount; i++)
        {
            String reviewsRepositoryItem = scanner.nextLine();
            reviewsRepository.add(reviewsRepositoryItem);
        }

        String userInput = scanner.nextLine();

        List<List<String>> solution = Suggestions.searchSuggestions(reviewsRepository, userInput);

        System.out.println(String.join((CharSequence) "\n", (CharSequence) solution.stream().map(x -> String.join(" ", x))));
    }

}
