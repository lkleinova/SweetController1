package sk.upjs.ics.android.sweetcontroller;

import android.provider.BaseColumns;

public interface Provider {
    public interface BreakfastTable extends BaseColumns {
        public static final String TABLE_NAME = "BreakfastTable";

        public static final String MEAL = "meal";

        public static final String CARBOHYDRATES = "carbohydrates"; //mnozstvo v gramoch, ktore tvori 1 sacharidovu jednotku

        public static final String NULL="nullColumn";
    }
}