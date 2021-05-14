package com.example.drugology;

import android.content.Context;
import android.net.ConnectivityManager;

public class IUPACImage {
    
    String[][] allImageUrl = new String[2][10];
    Context context;

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
    //0 anntibitoc
    //1 Antipyretic
    public IUPACImage(Context context) {
        this.context = context;
    }

    public IUPACImage() {
        allImageUrl[0][0] = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Tetracycline_numbering.svg/1200px-Tetracycline_numbering.svg.png";
        allImageUrl[0][1] = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Azithromycin_structure.svg/1200px-Azithromycin_structure.svg.png";
        allImageUrl[0][2] = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Chloramphenicol.svg/1200px-Chloramphenicol.svg.png";
        allImageUrl[0][3] = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Ampicillin_structure.svg/1200px-Ampicillin_structure.svg.png";
        allImageUrl[0][4] = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Isoniazid_skeletal.svg/1200px-Isoniazid_skeletal.svg.png";
        allImageUrl[0][5] = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Ciprofloxacin.svg/1200px-Ciprofloxacin.svg.png";
        allImageUrl[0][6] = "https://upload.wikimedia.org/wikipedia/commons/2/2c/Streptomycin.png";
        allImageUrl[0][7] = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Aciclovir_2D_structure.svg/1200px-Aciclovir_2D_structure.svg.png";
        allImageUrl[0][8] = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Metronidazol.svg/1200px-Metronidazol.svg.png";
        allImageUrl[0][9] = "https://www.chemspider.com/ImagesHandler.ashx?id=5142&w=250&h=250";

        allImageUrl[1][0] = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Naproxen2DACS.svg/1200px-Naproxen2DACS.svg.png";
        allImageUrl[1][1] = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Mefenamic_acid2DACS.svg/1200px-Mefenamic_acid2DACS.svg.png";
        allImageUrl[1][2] = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Piroxicam2DACS.svg/1200px-Piroxicam2DACS.svg.png";
        allImageUrl[1][3] = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Indometacin.svg/1200px-Indometacin.svg.png";
        allImageUrl[1][4] = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Metamizole2DACS.svg/220px-Metamizole2DACS.svg.png";
        allImageUrl[1][5] = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Diclofenac.svg/1200px-Diclofenac.svg.png";
        allImageUrl[1][6] = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/%28RS%29-Ibuprofen_Structural_Formula_V1.svg/200px-%28RS%29-Ibuprofen_Structural_Formula_V1.svg.png";
        allImageUrl[1][7] = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Nimesulide.svg/1200px-Nimesulide.svg.png";
        allImageUrl[1][8] = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Celecoxib2DACS.svg/1200px-Celecoxib2DACS.svg.png";
        allImageUrl[1][9] = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Paracetamol-skeletal.svg/1200px-Paracetamol-skeletal.svg.png";
    }

    public String AntibioticIUPAC(int position) {
        return allImageUrl[0][position];
    }

    public String AntipyreticIUPAC(int position) {
        return allImageUrl[1][position];
    }
}
