package dev.omedia.boot.domain;

public enum Label {
    MOBILE_NUM("მობ.ნომერი"),
    WORK_NUM("სამსახურის ნომერი"),
    HOME_NUM("სახლის ნომერი"),
    MAIL("პირადი მეილი"),
    WORK_MAIL("სამსახურის მეილი"),
    ADDRESS("მისამართი"),
    OTHER("სხვა");

    private String enumType;
    private Label(String type) {
        this.enumType = type;
    }

    public String getEnumType() {
        return enumType;
    }
}
