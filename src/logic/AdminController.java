package logic;

import dal.ServiceImpl;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class AdminController {

    public static void main (String[] args) {
        ServiceImpl serviceImpl = new ServiceImpl();

        new MainController(serviceImpl);
    }
}
