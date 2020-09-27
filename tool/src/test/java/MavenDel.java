import java.io.File;

public class MavenDel {

    private static  int count=0;

    public static void main(String[] args) {
        String mavenPath="";
        File file = new File(mavenPath);

        deleteDiGui(file);

        System.out.printf("-=-=-=--==执行完毕,删除数量:"+count);
    }

    private static void deleteDiGui(File file) {

        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                deleteDiGui(file1);
            }

        }else {
            if (file.getAbsolutePath().endsWith(".lastUpdated")){
                System.out.printf("--------->>>>"+file.getName());
                file.delete();
                count++;

            }
            if (file.getAbsolutePath().endsWith("_remote.repositories")){
                System.out.printf("=========>>>>"+file.getName());

                file.delete();
                count++;
            }
        }



    }


}


