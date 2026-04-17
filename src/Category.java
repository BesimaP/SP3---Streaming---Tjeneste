public enum Category {
    Crime("🔪"),
    War("⚔️"),
    Drama("🎭"),
    Family("👨‍👩‍👧"),
    Romance("❤️"),
    Sciencefiction("🚀"),
    Mystery("🔍");

    private final String smiley;

    Category(String smiley) {
        this.smiley = smiley;
    }

    public String getSmiley() {
        return smiley;
    }

    //eksempel: Category.Romance + " " + Category.Romance.getSmiley()
}