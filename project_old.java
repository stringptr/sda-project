import java.util.*;

class Utils {
  private static final int PADDING = 128;

  public class Print {
    public static void TopBottomBorder(int count, String c) {
      System.out.println(c.repeat(count));
    }

    public static void CenterTwoPadding(String content, String border, int outer_padding) {
      int total_padding = PADDING - (content.length() + (border.length() * 2) + (outer_padding * 2));
      int inner_padding_length = total_padding / 2;
      int otherinner_padding_length = total_padding - inner_padding_length;

      String innerPad = (" ").repeat(inner_padding_length);
      String other_inner_pad = (" ").repeat(otherinner_padding_length);
      String outer_pad = (" ").repeat(outer_padding);
      System.out.println(outer_pad + border + innerPad + content + other_inner_pad + border + outer_pad);
    }

    public static void Center(String texts, String border) {
      int total_pad = (PADDING - (border.length() * 2) - texts.length());
      int left_pad = total_pad / 2;
      int right_pad = total_pad - left_pad;
      System.out.println(border + " ".repeat(left_pad) + texts + " ".repeat(right_pad) + border);
    }

    public static void AnyColumn(LinkedList<String> texts, String border) {
      int total_pad = (PADDING - (border.length() * (texts.size() + 1))) / texts.size();
      for (String txt : texts) {
        int pad = total_pad - txt.length();
        int left_pad = (pad / 2);
        int right_pad = (pad - left_pad);
        System.out.print(border + " ".repeat(left_pad) + txt + " ".repeat(right_pad));
      }
      System.out.println(border);
    }
  }

  public class GetInput {
    public static final Scanner input = new Scanner(System.in);

    public static int integer(String prompt, String errortext, int padding) {
      int number;
      String beforePad = (" ").repeat(padding);
      while (true) {
        try {
          System.out.println(beforePad + prompt);
          number = input.nextInt();
          input.nextLine();
          return number;
        } catch (InputMismatchException e) {
          System.out.println(beforePad + errortext);
          input.nextLine();
        }
      }
    }

    public static int integerZeroPositiveCenter(String prompt, String errortext, int inputLength) {
      int leftErrorPadLength = (PADDING - errortext.length() - inputLength) / 2;
      String leftErrorPad = (" ").repeat(leftErrorPadLength);

      int number;
      while (true) {
        number = integerCenter(prompt, errortext, inputLength);
        if (number >= 0)
          return number;
        System.out.println(leftErrorPad + errortext);
      }
    }

    public static int integerPositiveCenter(String prompt, String errortext, int inputLength) {
      int leftErrorPadLength = (PADDING - errortext.length() - inputLength) / 2;
      String leftErrorPad = (" ").repeat(leftErrorPadLength);

      int number;
      while (true) {
        number = integerCenter(prompt, errortext, inputLength);
        if (number > 0)
          return number;
        System.out.println(leftErrorPad + errortext);
      }
    }

    public static int integerBoolCenter(String prompt, String errortext, int inputLength) {
      int leftErrorPadLength = (PADDING - errortext.length() - inputLength) / 2;
      String leftErrorPad = (" ").repeat(leftErrorPadLength);

      int number;
      while (true) {
        number = integerCenter(prompt, errortext, inputLength);
        if (number == 1 || number == 0)
          return number;
        System.out.println(leftErrorPad + errortext);
      }
    }

    public static int integerCenter(String prompt, String errortext, int inputLength) {
      int leftPromptPadLength = (PADDING - prompt.length() - inputLength) / 2;
      int leftErrorPadLength = (PADDING - errortext.length() - inputLength) / 2;
      String leftPromptPad = (" ").repeat(leftPromptPadLength);
      String leftErrorPad = (" ").repeat(leftErrorPadLength);

      int number;
      while (true) {
        try {
          System.out.println(leftPromptPad + prompt);
          number = input.nextInt();
          input.nextLine();
          return number;
        } catch (InputMismatchException e) {
          System.out.println(leftErrorPad + errortext + "\n");
          input.nextLine();
        }
      }
    }

    public static int integerPositive(String prompt, String errortext, int padding) {
      int number;
      String beforePad = (" ").repeat(padding);
      while (true) {
        number = integer(prompt, errortext, padding);
        if (number > 0)
          return number;
        System.out.println(beforePad + errortext);
      }
    }

    public static String stringLimited(String prompt, String errortext, int limit, int padding) {
      String sentences;
      String beforePad = (" ").repeat(padding);
      while (true) {
        System.out.println(beforePad + prompt);
        sentences = input.nextLine();

        if (sentences.length() <= limit)
          return sentences;
        System.out.println(beforePad + errortext);
      }
    }

    public static String stringLimitedCenter(String prompt, String errortext, int limit, int inputWidth) {
      int proomptTotalPad = 64 - prompt.length();
      if (proomptTotalPad < 0)
        proomptTotalPad = 0;
      int promptPadLength = proomptTotalPad / 2;

      int errorTotalPad = 64 - errortext.length();
      if (errorTotalPad < 0)
        errorTotalPad = 0;

      int inputTotalPad = 64 - inputWidth;
      if (inputTotalPad < 0)
        inputTotalPad = 0;
      int inputPadLength = inputTotalPad / 2;

      String promtPad = (" ").repeat(promptPadLength);
      String inputPad = (" ").repeat(inputPadLength);

      String sentences;

      while (true) {
        System.out.println(promtPad + prompt);
        System.out.println(inputPad);
        sentences = input.nextLine();

        if (sentences.length() <= limit)
          return sentences;

        Print.Center(errortext, "");
        System.out.println("");
      }

    }
  }

  public static void Wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static void ClearScreen() {
    System.out.println("\u001B[2J");
  }
}

class JobScheduling {
  public static class Job {
    String name;
    int deadline;
    int reward;
    int penalty;

    Job(String name, int deadline, int reward, int penalty) {
      this.name = name;
      this.deadline = deadline;
      this.reward = reward;
      this.penalty = penalty;
    }
  }

  private static Map<String, Job> jobs = new HashMap<>();
  private static Map<String, List<String>> graph = new HashMap<>();
  private static Map<String, Integer> indegree = new HashMap<>();

  public static boolean JobEmpty() {
    if (jobs == null || jobs.size() == 0) {
      return true;
    }
    return false;
  }

  public static void AddJob(String name, int deadline, int reward, int penalty) {
    jobs.put(name, new Job(name, deadline, reward, penalty));
  }
}

public class project_old {
  public static void main(String[] args) {
    String job_name, job_dependancy;
    int job_dl, job_reward, job_penalty;

    System.out.println("Name: ");
    job_name = Utils.GetInput.stringLimitedCenter("Name: ", "Name isn't valid", 16, 16);
    System.out.println("Deadline: ");
    job_dl = Utils.GetInput.integerPositiveCenter("Deadline: ", "Deadline isn't valid", 2);
    System.out.println("Completion Reward: ");
    job_reward = Utils.GetInput.integerPositiveCenter("Completion Reward: ", "Completion Reward isn't valid", 2);
    System.out.println("Penalty: ");
    job_penalty = Utils.GetInput.integerPositiveCenter("Penalty (If Not Completed): ", "Penalty isn't valid", 2);

    if (!JobScheduling.JobEmpty()) {
      System.out.println("Dependant on: ");
      job_dependancy = Utils.GetInput.stringLimitedCenter("Dependancy: ", "Dependancy isn't valid", 16, 16);
    }
  }
}
