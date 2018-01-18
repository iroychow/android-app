package com.example.ishitaroychowdhury.finalexam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ishitaroychowdhury on 12/11/17.
 */

public class GeocodeAPIFormat implements Serializable {


    @Override
    public String toString() {
        return "GeocodeAPIFormat{" +
                "status='" + status + '\'' +
                ", results=" + results +
                '}';
    }

    /**
     * results : [{"address_components":[{"long_name":"Charlotte","short_name":"Charlotte","types":["locality","political"]},{"long_name":"1, Charlotte","short_name":"1, Charlotte","types":["administrative_area_level_3","political"]},{"long_name":"Mecklenburg County","short_name":"Mecklenburg County","types":["administrative_area_level_2","political"]},{"long_name":"North Carolina","short_name":"NC","types":["administrative_area_level_1","political"]},{"long_name":"United States","short_name":"US","types":["country","political"]}],"formatted_address":"Charlotte, NC, USA","geometry":{"bounds":{"northeast":{"lat":35.39313300000001,"lng":-80.670104},"southwest":{"lat":35.0131739,"lng":-81.0095539}},"location":{"lat":35.2270869,"lng":-80.8431267},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":35.39313300000001,"lng":-80.670104},"southwest":{"lat":35.0131739,"lng":-81.0095539}}},"place_id":"ChIJgRo4_MQfVIgRZNFDv-ZQRog","types":["locality","political"]}]
     * status : OK
     */

    private String status;
    private List<ResultsBean> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        @Override
        public String toString() {
            return "ResultsBean{" +
                    "formatted_address='" + formatted_address + '\'' +
                    ", geometry=" + geometry +
                    ", place_id='" + place_id + '\'' +
                    ", address_components=" + address_components +
                    ", types=" + types +
                    '}';
        }

        /**
         * address_components : [{"long_name":"Charlotte","short_name":"Charlotte","types":["locality","political"]},{"long_name":"1, Charlotte","short_name":"1, Charlotte","types":["administrative_area_level_3","political"]},{"long_name":"Mecklenburg County","short_name":"Mecklenburg County","types":["administrative_area_level_2","political"]},{"long_name":"North Carolina","short_name":"NC","types":["administrative_area_level_1","political"]},{"long_name":"United States","short_name":"US","types":["country","political"]}]
         * formatted_address : Charlotte, NC, USA
         * geometry : {"bounds":{"northeast":{"lat":35.39313300000001,"lng":-80.670104},"southwest":{"lat":35.0131739,"lng":-81.0095539}},"location":{"lat":35.2270869,"lng":-80.8431267},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":35.39313300000001,"lng":-80.670104},"southwest":{"lat":35.0131739,"lng":-81.0095539}}}
         * place_id : ChIJgRo4_MQfVIgRZNFDv-ZQRog
         * types : ["locality","political"]
         */



        private String formatted_address;
        private GeometryBean geometry;
        private String place_id;
        private List<AddressComponentsBean> address_components;
        private List<String> types;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            @Override
            public String toString() {
                return "GeometryBean{" +
                        "bounds=" + bounds +
                        ", location=" + location +
                        ", location_type='" + location_type + '\'' +
                        ", viewport=" + viewport +
                        '}';
            }

            /**
             * bounds : {"northeast":{"lat":35.39313300000001,"lng":-80.670104},"southwest":{"lat":35.0131739,"lng":-81.0095539}}
             * location : {"lat":35.2270869,"lng":-80.8431267}
             * location_type : APPROXIMATE
             * viewport : {"northeast":{"lat":35.39313300000001,"lng":-80.670104},"southwest":{"lat":35.0131739,"lng":-81.0095539}}
             */



            private BoundsBean bounds;
            private LocationBean location;
            private String location_type;
            private ViewportBean viewport;

            public BoundsBean getBounds() {
                return bounds;
            }

            public void setBounds(BoundsBean bounds) {
                this.bounds = bounds;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class BoundsBean {
                /**
                 * northeast : {"lat":35.39313300000001,"lng":-80.670104}
                 * southwest : {"lat":35.0131739,"lng":-81.0095539}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 35.39313300000001
                     * lng : -80.670104
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 35.0131739
                     * lng : -81.0095539
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }

            public static class LocationBean {
                @Override
                public String toString() {
                    return "LocationBean{" +
                            "lat=" + lat +
                            ", lng=" + lng +
                            '}';
                }

                /**
                 * lat : 35.2270869
                 * lng : -80.8431267
                 */



                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":35.39313300000001,"lng":-80.670104}
                 * southwest : {"lat":35.0131739,"lng":-81.0095539}
                 */

                private NortheastBeanX northeast;
                private SouthwestBeanX southwest;

                public NortheastBeanX getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBeanX northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBeanX getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBeanX southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBeanX {
                    /**
                     * lat : 35.39313300000001
                     * lng : -80.670104
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBeanX {
                    /**
                     * lat : 35.0131739
                     * lng : -81.0095539
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            /**
             * long_name : Charlotte
             * short_name : Charlotte
             * types : ["locality","political"]
             */

            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }
    }
}
