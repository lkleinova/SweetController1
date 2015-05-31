package sk.upjs.ics.android.sweetcontroller;

import android.provider.BaseColumns;

/**
 * Created by Lenka on 27. 5. 2015.
 */
public interface ProviderLunchDinner {
    public interface LunchTable extends BaseColumns {
        public static final String TABLE_NAME = "LunchTable";

        public static final String MEAL = "meal";

        public static final String CARBOHYDRATES = "carbohydrates"; //mnozstvo v gramoch, ktore tvori 1 sacharidovu jednotku

        public static final String NULL="nullColumn";
    }

    public interface DinnerTable extends BaseColumns {
        public static final String TABLE_NAME = "DinnerTable";

        public static final String MEAL = "meal";

        public static final String CARBOHYDRATES = "carbohydrates"; //mnozstvo v gramoch, ktore tvori 1 sacharidovu jednotku

        public static final String NULL="nullColumn";
    }
}
