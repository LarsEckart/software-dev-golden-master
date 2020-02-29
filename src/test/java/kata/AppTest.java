package kata;

import java.util.List;
import java.util.Map;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AppTest {

  @Test
  void golden_master() {
    List<Performance> performances =
        List.of(
            new Performance("hamlet", 10),
            new Performance("as-like", 25),
            new Performance("othello", 20));

    Map<String, Play> plays =
        Map.of(
            "hamlet", new Play("Hamlet", "tragedy"),
            "as-like", new Play("As You Like It", "comedy"),
            "othello", new Play("Othello", "tragedy"));

    Invoice bigCo = new Invoice("BigCo", performances);
    String statement = new StatementPrinter().print(bigCo, plays);
    Approvals.verify(statement);
  }

  @Test
  void sampled_golden_master() {
    Integer[] tragedyAudience = {20, 29, 30, 31, 40};
    Integer[] comedyAUdience = {10, 19, 20, 21, 30};
    CombinationApprovals.verifyAllCombinations(
        this::print,
        tragedyAudience,
        comedyAUdience
    );
  }

  private String print(int tragedyAudience, int comedyAudience) {
    List<Performance> performances =
        List.of(
            new Performance("hamlet", tragedyAudience),
            new Performance("as-like", comedyAudience),
            new Performance("othello", 20));

    Map<String, Play> plays =
        Map.of(
            "hamlet", new Play("Hamlet", "tragedy"),
            "as-like", new Play("As You Like It", "comedy"),
            "othello", new Play("Othello", "tragedy"));

    Invoice bigCo = new Invoice("BigCo", performances);
    return new StatementPrinter().print(bigCo, plays);
  }
}
