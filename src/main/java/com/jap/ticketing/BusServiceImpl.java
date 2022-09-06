package com.jap.ticketing;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BusServiceImpl {

    public List<BusService> readFile(String fileName){
        List<BusService> busServices=new ArrayList<>();
        try {
            FileReader fileReader=new FileReader(fileName);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            while ((line=bufferedReader.readLine())!=null){
                String[] split=line.split(",");
                String schedule_no=split[0];
                String route_no=split[1];
                int ticket_from_stop_id=Integer.parseInt(split[2]);
                int ticket_from_stop_seq_no=Integer.parseInt(split[3]);
                int ticket_till_stop_id=Integer.parseInt(split[4]);
                int ticket_till_stop_seq_no=Integer.parseInt(split[5]);
                String ticket_date=split[6];
                String ticket_time=split[7];
                int total_ticket_amount=Integer.parseInt(split[8]);
                double travelled_KM=Double.parseDouble(split[9]);

                busServices.add(new BusService(schedule_no,route_no,ticket_from_stop_id,ticket_from_stop_seq_no,ticket_till_stop_id,ticket_till_stop_seq_no,
                        ticket_date,ticket_time,total_ticket_amount,travelled_KM));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return busServices;
    }
    public List<BusService> getdistanceTravelled(List<BusService> busServices){
       busServices.sort((d1,d2)-> {
            if (d1.getTravelled_KM()==d2.getTravelled_KM()){
                return 0;
            } else if (d1.getTravelled_KM()>d2.getTravelled_KM()) {
                return -1;
            }else {
                return 1;
            }
        });
        return busServices;
    }


   public int getTotalAmount(List<BusService> busServices) {

       AmountCollection amountCollection = (busServices1) -> {
           int sum = 0;
           Iterator<BusService> iterator = busServices.iterator();
           while (iterator.hasNext()) {
               BusService element = iterator.next();
               sum = sum + element.getTotal_ticket_amount();
           }
           return sum;
       };
       return amountCollection.add(busServices);
   }

    public static void main(String[] args) {
        BusServiceImpl busServiceImpl=new BusServiceImpl();
        List<BusService> busServices=busServiceImpl.readFile("sample.csv");
        for (BusService element:busServices){
            System.out.println(element);
        }
        System.out.println("------------------------------------------------"  );
        System.out.println();

        System.out.println(busServiceImpl.getdistanceTravelled(busServices));

        System.out.println("------------------------------------------------"  );
        System.out.println();
        System.out.println("Total collection " + busServiceImpl.getTotalAmount(busServices));




       
       

    }
}




