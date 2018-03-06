package cn.zucc.pump;

import cn.zucc.pump.service.HostController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        RemoteDataBaseManager dataBaseManager = new RemoteDataBaseManager("127.0.0.1",3306,"root","root");
//        Statement statement = dataBaseManager.createDataBase("hello");
//        List<ObjectPO> list =  LocalDataBaseManager.getInstance().getMapper(ObjectService.class).queryByName("水厂");
//        System.out.println(list.size());
//        System.out.println(list.get(0).getDatabase_name());
//        HostController hostService = new HostController();
//        hostService.changeHost(3,"127.0.0.1",3305,"123","123");
//        hostService.deleteHost(4);
//       List<Host> list = hostService.findAllHost();
//       if(list!=null){
//           for(int i=0;i<list.size();i++){
//               System.out.println(list.get(i).getIp());
//           }
//       }
//        Connection connection = hostService.connectHost(1);
//        System.out.println(connection);
//        ObjectController objectService = new ObjectController();
//        objectService.createObject(1,1,"hello","水厂","城院","大哥","17764525459");
//        objectService.deleteObject(3);
//        objectService.changeObject(2,2,"111","22","123","21","13655");
//        List<ObjectPO> list = objectService.findAllObject();
//        System.out.println(list.size());
//        Connection connection = objectService.connectDatabase(4);
//        System.out.println(connection);

//        DeviceController deviceService = new DeviceController();
//        System.out.println(deviceService.findAllAttrType());
//        deviceService.createDevice(2,3);
//        deviceService.deleteDevice(10);
//        deviceService.createDeviceAttr(1,2,"222");
//        deviceService.deleteDeviceAttr(3);
//        deviceService.changeAttr(2,"123");
//        deviceService.createTable(1);
//        List<Integer> addAttr = new ArrayList<Integer>();
//        List<Integer> delAttr = new ArrayList<Integer>();
//        addAttr.add(2);
//        delAttr.add(1);
//        deviceService.changeTable(1,addAttr,delAttr);
//        RemoteDataBaseManager.connectDataBase("127.0.0.1",3306,"root","root","hello");
//        RemoteDataBaseManager.connectHost("127.0.0.1",3306,"root","root");
//        ObjectController objectService = new ObjectController();
//        objectService.deleteObjectInfo(1);
        HostController hostService = new HostController();
//        hostService.createHost("127.0.0.1",3306,"root","root");
        hostService.connectHost(1);

    }

}
