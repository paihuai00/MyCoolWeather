package com.example.cuishuxiang.mycoolweather.bean_db;

import java.util.List;

/**
 * @author cuishuxiang
 * @date 2017/11/2.
 *
 * 空气质量
 */

public class AirQualityBean {

    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * air_now : {"air_city":{"aqi":"140","co":"2","main":"PM2.5","no2":"88","o3":"15","pm10":"136","pm25":"107","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"17"},"air_station":[{"air_sta":"万寿西宫","aqi":"176","asid":"CNA1001","co":"2.3","lat":"39.8673","lon":"116.366","main":"PM2.5","no2":"117","o3":"6","pm10":"176","pm25":"133","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"23"},{"air_sta":"定陵","aqi":"90","asid":"CNA1002","co":"0.9","lat":"40.2865","lon":"116.17","main":"PM2.5","no2":"59","o3":"33","pm10":"102","pm25":"67","pub_time":"2017-11-02 11:00","qlty":"良","so2":"6"},{"air_sta":"东四","aqi":"175","asid":"CNA1003","co":"2.3","lat":"39.9522","lon":"116.434","main":"PM2.5","no2":"101","o3":"5","pm10":"180","pm25":"132","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"32"},{"air_sta":"天坛","aqi":"180","asid":"CNA1004","co":"2.0","lat":"39.8745","lon":"116.434","main":"PM2.5","no2":"113","o3":"14","pm10":"189","pm25":"136","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"21"},{"air_sta":"农展馆","aqi":"169","asid":"CNA1005","co":"2.2","lat":"39.9716","lon":"116.473","main":"PM2.5","no2":"103","o3":"11","pm10":"166","pm25":"128","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"33"},{"air_sta":"官园","aqi":"155","asid":"CNA1006","co":"1.9","lat":"39.9425","lon":"116.361","main":"PM2.5","no2":"100","o3":"11","pm10":"186","pm25":"118","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"25"},{"air_sta":"海淀区万柳","aqi":"128","asid":"CNA1007","co":"1.8","lat":"39.9934","lon":"116.315","main":"PM2.5","no2":"104","o3":"10","pm10":"138","pm25":"97","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"20"},{"air_sta":"顺义新城","aqi":"82","asid":"CNA1008","co":"1.1","lat":"40.1438","lon":"116.72","main":"PM2.5","no2":"53","o3":"23","pm10":"87","pm25":"60","pub_time":"2017-11-02 11:00","qlty":"良","so2":"12"},{"air_sta":"怀柔镇","aqi":"89","asid":"CNA1009","co":"0.7","lat":"40.3937","lon":"116.644","main":"PM2.5","no2":"53","o3":"28","pm10":"92","pm25":"66","pub_time":"2017-11-02 11:00","qlty":"良","so2":"3"},{"air_sta":"昌平镇","aqi":"93","asid":"CNA1010","co":"1.0","lat":"40.1952","lon":"116.23","main":"PM2.5","no2":"73","o3":"17","pm10":"120","pm25":"69","pub_time":"2017-11-02 11:00","qlty":"良","so2":"6"},{"air_sta":"奥体中心","aqi":"148","asid":"CNA1011","co":"2.2","lat":"40.0031","lon":"116.407","main":"PM2.5","no2":"111","o3":"9","pm10":"141","pm25":"113","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"19"},{"air_sta":"古城","aqi":"128","asid":"CNA1012","co":"1.5","lat":"39.9279","lon":"116.225","main":"PM2.5","no2":"70","o3":"22","pm10":"130","pm25":"97","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"14"}]}
         * basic : {"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.40528870","tz":"+8.0"}
         * status : ok
         * update : {"loc":"2017-11-02 12:48","utc":"2017-11-02 04:48"}
         */

        private AirNowBean air_now;
        private BasicBean basic;
        private String status;
        private UpdateBean update;

        public AirNowBean getAir_now() {
            return air_now;
        }

        public void setAir_now(AirNowBean air_now) {
            this.air_now = air_now;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public static class AirNowBean {
            /**
             * air_city : {"aqi":"140","co":"2","main":"PM2.5","no2":"88","o3":"15","pm10":"136","pm25":"107","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"17"}
             * air_station : [{"air_sta":"万寿西宫","aqi":"176","asid":"CNA1001","co":"2.3","lat":"39.8673","lon":"116.366","main":"PM2.5","no2":"117","o3":"6","pm10":"176","pm25":"133","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"23"},{"air_sta":"定陵","aqi":"90","asid":"CNA1002","co":"0.9","lat":"40.2865","lon":"116.17","main":"PM2.5","no2":"59","o3":"33","pm10":"102","pm25":"67","pub_time":"2017-11-02 11:00","qlty":"良","so2":"6"},{"air_sta":"东四","aqi":"175","asid":"CNA1003","co":"2.3","lat":"39.9522","lon":"116.434","main":"PM2.5","no2":"101","o3":"5","pm10":"180","pm25":"132","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"32"},{"air_sta":"天坛","aqi":"180","asid":"CNA1004","co":"2.0","lat":"39.8745","lon":"116.434","main":"PM2.5","no2":"113","o3":"14","pm10":"189","pm25":"136","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"21"},{"air_sta":"农展馆","aqi":"169","asid":"CNA1005","co":"2.2","lat":"39.9716","lon":"116.473","main":"PM2.5","no2":"103","o3":"11","pm10":"166","pm25":"128","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"33"},{"air_sta":"官园","aqi":"155","asid":"CNA1006","co":"1.9","lat":"39.9425","lon":"116.361","main":"PM2.5","no2":"100","o3":"11","pm10":"186","pm25":"118","pub_time":"2017-11-02 11:00","qlty":"中度污染","so2":"25"},{"air_sta":"海淀区万柳","aqi":"128","asid":"CNA1007","co":"1.8","lat":"39.9934","lon":"116.315","main":"PM2.5","no2":"104","o3":"10","pm10":"138","pm25":"97","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"20"},{"air_sta":"顺义新城","aqi":"82","asid":"CNA1008","co":"1.1","lat":"40.1438","lon":"116.72","main":"PM2.5","no2":"53","o3":"23","pm10":"87","pm25":"60","pub_time":"2017-11-02 11:00","qlty":"良","so2":"12"},{"air_sta":"怀柔镇","aqi":"89","asid":"CNA1009","co":"0.7","lat":"40.3937","lon":"116.644","main":"PM2.5","no2":"53","o3":"28","pm10":"92","pm25":"66","pub_time":"2017-11-02 11:00","qlty":"良","so2":"3"},{"air_sta":"昌平镇","aqi":"93","asid":"CNA1010","co":"1.0","lat":"40.1952","lon":"116.23","main":"PM2.5","no2":"73","o3":"17","pm10":"120","pm25":"69","pub_time":"2017-11-02 11:00","qlty":"良","so2":"6"},{"air_sta":"奥体中心","aqi":"148","asid":"CNA1011","co":"2.2","lat":"40.0031","lon":"116.407","main":"PM2.5","no2":"111","o3":"9","pm10":"141","pm25":"113","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"19"},{"air_sta":"古城","aqi":"128","asid":"CNA1012","co":"1.5","lat":"39.9279","lon":"116.225","main":"PM2.5","no2":"70","o3":"22","pm10":"130","pm25":"97","pub_time":"2017-11-02 11:00","qlty":"轻度污染","so2":"14"}]
             */

            private AirCityBean air_city;
            private List<AirStationBean> air_station;

            public AirCityBean getAir_city() {
                return air_city;
            }

            public void setAir_city(AirCityBean air_city) {
                this.air_city = air_city;
            }

            public List<AirStationBean> getAir_station() {
                return air_station;
            }

            public void setAir_station(List<AirStationBean> air_station) {
                this.air_station = air_station;
            }

            public static class AirCityBean {
                /**
                 * aqi : 140
                 * co : 2
                 * main : PM2.5
                 * no2 : 88
                 * o3 : 15
                 * pm10 : 136
                 * pm25 : 107
                 * pub_time : 2017-11-02 11:00
                 * qlty : 轻度污染
                 * so2 : 17
                 */

                private String aqi;
                private String co;
                private String main;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String pub_time;
                private String qlty;
                private String so2;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getMain() {
                    return main;
                }

                public void setMain(String main) {
                    this.main = main;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getPub_time() {
                    return pub_time;
                }

                public void setPub_time(String pub_time) {
                    this.pub_time = pub_time;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }

            public static class AirStationBean {
                /**
                 * air_sta : 万寿西宫
                 * aqi : 176
                 * asid : CNA1001
                 * co : 2.3
                 * lat : 39.8673
                 * lon : 116.366
                 * main : PM2.5
                 * no2 : 117
                 * o3 : 6
                 * pm10 : 176
                 * pm25 : 133
                 * pub_time : 2017-11-02 11:00
                 * qlty : 中度污染
                 * so2 : 23
                 */

                private String air_sta;
                private String aqi;
                private String asid;
                private String co;
                private String lat;
                private String lon;
                private String main;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String pub_time;
                private String qlty;
                private String so2;

                public String getAir_sta() {
                    return air_sta;
                }

                public void setAir_sta(String air_sta) {
                    this.air_sta = air_sta;
                }

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getAsid() {
                    return asid;
                }

                public void setAsid(String asid) {
                    this.asid = asid;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLon() {
                    return lon;
                }

                public void setLon(String lon) {
                    this.lon = lon;
                }

                public String getMain() {
                    return main;
                }

                public void setMain(String main) {
                    this.main = main;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getPub_time() {
                    return pub_time;
                }

                public void setPub_time(String pub_time) {
                    this.pub_time = pub_time;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicBean {
            /**
             * cid : CN101010100
             * location : 北京
             * parent_city : 北京
             * admin_area : 北京
             * cnty : 中国
             * lat : 39.90498734
             * lon : 116.40528870
             * tz : +8.0
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2017-11-02 12:48
             * utc : 2017-11-02 04:48
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
    }
}
