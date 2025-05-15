import java.util.List;
public class DietPlan {
    private String planId;
    private String description;
    private List<String> meals;

    public DietPlan(String planId, String description, List<String> meals) {
        this.planId = planId;
        this.description = description;
        this.meals = meals;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
public List<String> getMeals() {
        return meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public void displayPlan() {
        System.out.println("Diet Plan ID: " + planId);
        System.out.println("Description: " + description);
        System.out.println("Meals:");
        for (String meal : meals) {
            System.out.println("- " + meal);
        }
    }
}
