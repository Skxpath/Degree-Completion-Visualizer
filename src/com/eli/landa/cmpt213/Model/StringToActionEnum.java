package com.eli.landa.cmpt213.Model;

/**
 * Created by Aria on 3/28/2017.
 * <p>
 * Converts an actionVal into an ActionEnum before inputting into Action class
 */
public class StringToActionEnum {

    public static ActionEnum convert(String actionVal) {
        switch (actionVal) {
            case "admt":
                return ActionEnum.ADMT;

            case "add":
                return ActionEnum.ADD;

            case "fin":
                return ActionEnum.FIN;

            case "dropout":
                return ActionEnum.DROPOUT;

            default:
                return ActionEnum.NO_ACTION; //In the case when there is no action identified. Return this for error checking in Action class.
        }
    }
}

