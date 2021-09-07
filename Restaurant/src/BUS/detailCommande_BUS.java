package BUS;

import java.util.ArrayList;

import DAO.detailCommande_DAO;
import DTO.detailCommande;

public class detailCommande_BUS {
	private ArrayList<detailCommande> listeDetailCommande;
    private detailCommande_DAO detailCmdDAO = new detailCommande_DAO();
    private facture_BUS factureBUS = new facture_BUS();

    public void lireListeDetail() {
        this.listeDetailCommande = detailCmdDAO.getListeDetailCommande();
    }

    public ArrayList<detailCommande> getListeDetailCommande() {
    	listeDetailCommande = null;
    	lireListeDetail();
        return listeDetailCommande;
    }

    /*public ArrayList<detailCommande> getListCTHoaDonTheoMaHD(String maHD) {
        int ma = Integer.parseInt(maHD);
        ArrayList<CTHoaDon> dsct = new ArrayList<>();

        for (CTHoaDon cthd : listeDetailCommande) {
            if (cthd.getMaHD() == ma)
                dsct.add(cthd);
        }

        return dsct;
    }

    public void addCTHoaDon(String maSP, String soLuong, String donGia, String thanhTien) {
        int ma = hdBUS.getMaHoaDonMoiNhat();

        donGia = donGia.replace(",","");
        thanhTien = thanhTien.replace(",", "");

        CTHoaDon cthd = new CTHoaDon();

        cthd.setMaHD(ma);
        cthd.setMaSP(Integer.parseInt(maSP));
        cthd.setDonGia(Integer.parseInt(donGia));
        cthd.setSoLuong(Integer.parseInt(soLuong));
        cthd.setThanhTien(Integer.parseInt(thanhTien));

        detailCmdDAO.addCTHoaDon(cthd);
    }*/
}
