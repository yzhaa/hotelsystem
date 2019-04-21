package Storage;

import com.yzh.www.entity.Hotel;

/**
 * 用于客户选择酒店之后对酒店的暂存
 */

public class HotelStorage {
    private static Hotel hotel;

    public HotelStorage(){}

    public  Hotel getHotel() {
        return hotel;
    }
    public  void setHotel(Hotel hotel){
       HotelStorage.hotel=hotel;
    }
}
