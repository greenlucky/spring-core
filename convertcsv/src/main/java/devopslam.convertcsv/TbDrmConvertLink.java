package devopslam.convertcsv;

import java.io.*;
import java.util.Arrays;

public class TbDrmConvertLink {

    public static void main(String[] args) {
        String inputCSV = "C:\\Users\\lam.nm\\Documents\\spring-core\\convertcsv\\src\\main\\resources\\export_tbl_drmlink_20170519.csv";
        String outputCSV = "";

        String header = "recv_time,con_type,gid,Fno,map_ver,Mcd,drm_mc,drm_cn1,drm_cn2,drm_rn,aprc_time,leav_time,jam,tt,drm_speed,drm_rl,next_mc,next_cn1,next_cn2,next_rn,Pattern,acd,lid,dif,dir";

        BufferedReader br = null;
        PrintWriter pw = null;
        String line = "";
        String csvSplitBy = ",";


        try {
            br = new BufferedReader(new FileReader(inputCSV));

            // skip first line
            br.readLine();

            while ((line = br.readLine()) != null) {
                StringBuilder stringBuilder = new StringBuilder();
                String[] lines = line.trim().split(csvSplitBy);
                String[] mainData = Arrays.copyOfRange(lines, 0, 20);
                String mainLine = String.join(",", mainData);
                stringBuilder.append(mainLine);
                int breakLine = 0;
                for (int i = 21; i < lines.length; i++) {
                    stringBuilder.append(",");
                    stringBuilder.append(lines[i].toString());
                    breakLine++;
                    if (breakLine == 4) {
                        breakLine = 0;
                        stringBuilder.append("\n");
                        if(lines.length - 1 > i) stringBuilder.append(mainLine);
                    }
                }
                System.out.println(stringBuilder.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    //pw.write(stringBuilder.toString());
                    //pw.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
