package testChinaRailway;

public enum Lines {

    Beijing_Guangzhou {
        @Override
        public String toString(){
            return "京广线";
        }

    },
    Beijing_HKWKowloog {
        @Override
        public String toString() {
            return "京九线";
        }
    },
    LanZhou_LianYun {
        @Override
        public String toString() {
            return "陇海线";
        }
    },
}

