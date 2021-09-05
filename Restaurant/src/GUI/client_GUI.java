package GUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.client_BUS;
import Custom.monTableau;
import Custom.transparentPanel;

import static Main.main.changeLNF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class client_GUI extends JPanel{
	
	public client_GUI() {
		changeLNF("FlatLaf");
		addControls();
        addEvents();
	}
	
	
	private client_BUS clientBUS = new client_BUS();

    final Color colorPanel = new Color(247, 247, 247);
    JButton btnReset;
    JTextField txtIdClient, txtNom, txtPrenom, txtAdresse, txtNumTel, txtEmail, txtPoint, txtCleRecherche;
    JButton btnAjoute, btnModif, btnRecherhce;
    monTableau tabClient;
    DefaultTableModel modelTabClient;

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);


        /*
        =========================================================================
                                    PANEL CLIENT
        =========================================================================
         */
        JPanel panelClient = new transparentPanel();
        panelClient.setLayout(new BoxLayout(panelClient, BoxLayout.Y_AXIS));

        JPanel panelTop = new transparentPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

        JPanel pnTitle = new transparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUáº¢N LÃ� KHÃ�CH HÃ€NG</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        panelTop.add(pnTitle);

        //======PANEL TEXT FIELD=======
        JPanel pnTextField = new transparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblMa, lblHo, lblTen, lblGioiTinh, lblTongChiTieu;
        lblMa = new JLabel("MÃ£ KhÃ¡ch hÃ ng");
        lblHo = new JLabel("Há»� Ä‘á»‡m");
        lblTen = new JLabel("TÃªn");
        lblGioiTinh = new JLabel("Giá»›i tÃ­nh");
        lblTongChiTieu = new JLabel("Tá»•ng chi tiÃªu");

        lblMa.setFont(font);
        lblHo.setFont(font);
        lblTen.setFont(font);
        lblGioiTinh.setFont(font);
        lblTongChiTieu.setFont(font);

        txtIdClient = new JTextField(20);
        txtIdClient.setEditable(false);
        txtNom = new JTextField(20);
        txtPrenom = new JTextField(20);
        txtTongChiTieu = new JTextField(20);
        txtTongChiTieu.setEditable(false);
        cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.addItem("Chá»�n giá»›i tÃ­nh");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Ná»¯");

        txtIdClient.setFont(font);
        txtNom.setFont(font);
        txtPrenom.setFont(font);
        txtTongChiTieu.setFont(font);
        cmbGioiTinh.setFont(font);

        JPanel pnMa = new transparentPanel();
        pnMa.add(lblMa);
        pnMa.add(txtIdClient);
        pnTextField.add(pnMa);

        JPanel pnHo = new transparentPanel();
        pnHo.add(lblHo);
        pnHo.add(txtNom);
        pnTextField.add(pnHo);

        JPanel pnTen = new transparentPanel();
        pnTen.add(lblTen);
        pnTen.add(txtPrenom);
        pnTextField.add(pnTen);

        JPanel pnGioiTinh = new transparentPanel();
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(cmbGioiTinh);
        pnTextField.add(pnGioiTinh);

        JPanel pnTongChiTieu = new transparentPanel();
        pnTongChiTieu.add(lblTongChiTieu);
        pnTongChiTieu.add(txtTongChiTieu);
        pnTextField.add(pnTongChiTieu);

        Dimension lblSize = lblMa.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblHo.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblGioiTinh.setPreferredSize(lblSize);
        lblTongChiTieu.setPreferredSize(lblSize);
        cmbGioiTinh.setPreferredSize(txtNom.getPreferredSize());

        panelTop.add(pnTextField);
        panelClient.add(panelTop);

        //===============PANEL BUTTON=============
        JPanel pnButton = new transparentPanel();
        btnAjoute = new JButton("ThÃªm");
        btnSua = new JButton("LÆ°u");
        btnXoa = new JButton("XoÃ¡");

        btnAjoute.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);

        btnAjoute.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        panelClient.add(pnButton);

        pnButton.add(btnAjoute);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        btnAjoute.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        Dimension btnSize = btnAjoute.getPreferredSize();
        btnAjoute.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);

        //====PANEL SEARCH=====
        JPanel pnTimKiem = new transparentPanel();
        JLabel lblTimKiem = new JLabel("Tá»« khoÃ¡ tÃ¬m");
        lblTimKiem.setFont(font);
        txtTukhoa = new JTextField(20);
        txtTukhoa.setFont(font);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTukhoa);
        panelClient.add(pnTimKiem);

        JPanel pnTimGioiHan = new transparentPanel();
        JLabel lblMin = new JLabel("Chi tiÃªu tá»«:");
        JLabel lblMax = new JLabel("Ä‘áº¿n:");
        lblMin.setFont(font);
        lblMax.setFont(font);
        txtMinchiTieu = new JTextField(5);
        txtMinchiTieu.setHorizontalAlignment(JTextField.CENTER);
        txtIdClientxChiTieu = new JTextField(5);
        txtIdClientxChiTieu.setHorizontalAlignment(JTextField.CENTER);
        txtMinchiTieu.setFont(font);
        txtIdClientxChiTieu.setFont(font);
        btnTim = new JButton(new ImageIcon("image/Search-icon.png"));
        btnTim = new JButton(new ImageIcon("image/Search-icon.png"));
        pnTimGioiHan.add(lblMin);
        pnTimGioiHan.add(txtMinchiTieu);
        pnTimGioiHan.add(lblMax);
        pnTimGioiHan.add(txtIdClientxChiTieu);
        pnTimGioiHan.add(btnTim);
        panelClient.add(pnTimGioiHan);
        //=========================TABLE=====================
        modelTabClient = new DefaultTableModel();
        modelTabClient.addColumn("MÃ£ KH");
        modelTabClient.addColumn("Há»� Ä‘á»‡m");
        modelTabClient.addColumn("TÃªn");
        modelTabClient.addColumn("Giá»›i tÃ­nh");
        modelTabClient.addColumn("Tá»•ng chi tiÃªu");

        tabClient = new MyTable(modelTabClient);

        JScrollPane scrtabClient = new JScrollPane(tabClient);

        this.add(panelClient, BorderLayout.NORTH);
        this.add(scrtabClient, BorderLayout.CENTER);

        loadDataLenTableKhachHang();
    }

    private void addEvents() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTableKhachHang();
                txtIdClient.setText("");
                txtNom.setText("");
                txtPrenom.setText("");
                txtTongChiTieu.setText("");
                txtTukhoa.setText("");
                txtMinchiTieu.setText("");
                txtIdClientxChiTieu.setText("");
                cmbGioiTinh.setSelectedIndex(0);
            }
        });

        tabClient.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClicktabClient();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        txtTukhoa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }
        });

        txtMinchiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdClientxChiTieu.requestFocus();
            }
        });

        txtIdClientxChiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTim.doClick();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemTheoKhoang();
            }
        });

        btnAjoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyajouteClient();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhachHang();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLysuppressionClient();
            }
        });
    }

    private void loadDataLenTableKhachHang() {
        client_BUS.docDanhSach();
        ArrayList<KhachHang> dskh = client_BUS.getListKhachHang();
        loadDataLenTableKhachHang(dskh);
    }

    private void loadDataLenTableKhachHang(ArrayList<KhachHang> dskh) {
        modelTabClient.setRowCount(0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (KhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(kh.getGioiTinh());
            vec.add(dcf.format(kh.getTongChiTieu()));
            modelTabClient.addRow(vec);
        }
    }

    private void xuLyClicktabClient() {
        int row = tabClient.getSelectedRow();
        if (row > -1) {
            txtIdClient.setText(tabClient.getValueAt(row, 0) + "");
            txtNom.setText(tabClient.getValueAt(row, 1) + "");
            txtPrenom.setText(tabClient.getValueAt(row, 2) + "");
            int index = tabClient.getValueAt(row, 3).equals("Nam") ? 1 : 2;
            txtTongChiTieu.setText(tabClient.getValueAt(row, 4) + "");
        }
    }

    private void xuLyTimKiemTheoKhoang() {
        ArrayList<KhachHang> dskh = client_BUS.timKiemKhachHang(txtMinchiTieu.getText(), txtIdClientxChiTieu.getText());
        if (dskh == null)
            return;
        loadDataLenTableKhachHang(dskh);
    }

    private void xuLyLiveSearch() {
        ArrayList<KhachHang> dskh = client_BUS.timKiemKhachHang(txtTukhoa.getText());
        loadDataLenTableKhachHang(dskh);
    }

    private void xuLyajouteClient() {
        if (client_BUS.ajouteClient(txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtNumTel.getText(), txtEmail.getText()))
            btnReset.doClick();
    }

    private void xuLySuaKhachHang() {
        if (clientBUS.majClient(txtIdClient.getText(), txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtNumTel.getText(), txtEmail.getText()))
            btnReset.doClick();
    }


}
