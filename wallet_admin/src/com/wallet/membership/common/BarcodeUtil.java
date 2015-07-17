package com.wallet.membership.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.ibatis.session.SqlSession;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.wallet.common.util.PropertiesUtil;
import com.wallet.membership.service.custom.CouponService;


public class BarcodeUtil {

	private int XSIZE;
	private int YSIZE;
	private int FSIZE;
	private int FXLOC;
	private int FYLOC;
	private int FXBARLOC;
	private int FYBARLOC;
	private int FYBARSIZE;
	private int XBARLOC;
	private int YBARLOC;
	private int XBARSIZE;
	private int YBARSIZE;
	private int FWIDTH;
	private int STYLE;

	//private ArrayList<IsMode> IsMode = null;

	private String TotalPath;
	private String Host;
	private String MemberPath;
	private String CouponPath;
	private String S_BarcodePath;
	private String BarcodePath;
	private String FullPath;
	private String B_BarcodePath;
	private File BFile, SFile;

	private String Os;
	private String MembId;
	private String CardLevel;
	private String ValSday;
	private String ValEday;
	private String Name;
	private String BarType;

	private String BarcodeStr;
	private String UserID;
	private boolean isCoupon = false;
	
	// java font style :  Serif, SansSerif, DialogInput, Dialog, and DialogInput
	public static void main(String[] args) {
		/*
		 * 2b_asia 인경우 바코드 번호만 생성
		 */
		BarcodeUtil barcodeUtil = new BarcodeUtil("6571", "i", "my_asia", "1",
				"LEE JEONGIN", "20120101", "20120101", "03", false);
		/**
		 * 1 : 바코드번호 2 : 저장 Path
		 * */
		barcodeUtil.Make("123456789012", 1);
	}

	public BarcodeUtil() {
		// TODO Auto-generated constructor stub
	}

	public BarcodeUtil(String UserID, String Os, String MembId, String CardLevel, String Name,
			String ValSday, String ValEday, String BarType, boolean isCoupon) {
		// TODO Auto-generated constructor stub

		this.Os = Os;
		this.MembId = MembId;
		this.CardLevel = CardLevel;
		this.BarType = BarType;
		this.Name = Name;
		this.UserID = UserID;
		this.isCoupon = isCoupon;
		
		if (MembId.equals("my_asia") && this.isCoupon == false) {
			try {
				if (!ValEday.equals("")) {
					//[20121106][inoky@2bsolution.com] : endday 000000에대한 처리
					if (Integer.parseInt(ValEday) != 0) {
						Date isEVal = DateTime.parse(ValEday, "yyyyMMdd");
						this.ValEday = DateTime.format(isEVal, "MM/yyyy");
						this.ValSday = "";
					} else {
						this.ValSday = "";
						this.ValEday = "";
					}
				} else {
					this.ValSday = "";
					this.ValEday = "";
				}
			} catch (Exception e) {
				// TODO: handle exception
				this.ValSday = "";
				this.ValEday = "";
			}
		} else {
			try {
				if (!ValSday.equals("") && !ValEday.equals("")) {
					Date isSVal = DateTime.parse(ValSday, "yyyyMMdd");
					Date isEVal = DateTime.parse(ValEday, "yyyyMMdd");
					this.ValSday = DateTime.format(isSVal, "yyyy/MM/dd");
					this.ValEday = DateTime.format(isEVal, "yyyy/MM/dd");
				} else {
					this.ValSday = "";
					this.ValEday = "";
				}
			} catch (Exception e) {
				// TODO: handle exception
				this.ValSday = "";
				this.ValEday = "";
			}
		}
	}

	public BarcodeUtil(String UserID) {

		if (UserID == null)
			return;
		if (UserID.equals(""))
			return;

		String width = getDeviceWdith(UserID);
		SetUbpayBarcode(width);
	}

	public void SetUbpayBarcode(String width) {
		/*
		InputStream is = getClass().getResourceAsStream("/resources/barcode.properties");
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Host = props.getProperty("host");
		String path = props.getProperty("ubpay_path");
		*/


		String path = PropertiesUtil.get("ubpay_path");
		Host = PropertiesUtil.get("img_host");


		if (width.equals("320"))
			path += "320.png";
		else if (width.equals("540"))
			path += "540.png";
		else if (width.equals("800"))
			path += "800.png";
		else if (width.equals("720"))
			path += "720.png";
		else if (width.equals("768"))
			path += "768.png";
		else if (width.equals("640"))
			path += "640.png";
		else
			path += "480.png";

		setS_BarcodePath(path);
	}

	public void Make(String code, int i) {
		BarcodeMaking(code, i, true);
		BarcodeMaking(code, i, false);
	}

	/**
	 * @author 이경훈
	 * */
	public void BarcodeMaking(String code, int i, boolean isBigUrl) {
		if (code == null || code.equals("")) {
			return;
		}
		BarcodeStr = code;

		if (MembId.equals("spc")) {
			BarcodeStr = SpcGen.SPCBarcodeGenMake(code);
		}

		File isFile = null;
		String filename = DateTime.format("yyyyMMddHHmmssSSS") + ".png";
		if (isBigUrl) {
			isFile = makeBarcode(BarcodeStr, "B" + filename, i);
			B_BarcodePath = BarcodePath;
		} else {
			isFile = makeBarcode(BarcodeStr, "M" + filename, i);
			S_BarcodePath = BarcodePath;
		}

		setBarcodeStr();

		//[20121107][inoky@2bsolution.com] : width값에 따라 바코드 각각 그려주기
		setRatioSmaillBarcode(isBigUrl, BarcodeStr);
		BMake(isFile, BarcodeStr);
	}

	/**
	 * 바코드 생성
	 *
	 * @author 이경훈
	 * */
	private void BMake(File isFile, String BarcodeStr) {
		try {
			final BufferedImage Barcodeimage = ImageIO.read(isFile);
			final BufferedImage image = new BufferedImage(XSIZE, YSIZE,
					BufferedImage.TYPE_INT_BGR);

			Color bg = new Color(Integer.parseInt("FFFFFF", 16));
			Color fg = new Color(Integer.parseInt("000000", 16));
			Graphics g = image.getGraphics();

			// 이미지 생성
			g.setColor(bg);
			g.fillRect(0, 0, XSIZE, YSIZE);

			if ((MembId.equals("my_asia") && this.isCoupon == true) || !MembId.equals("my_asia")) {
				g.drawImage(Barcodeimage, XBARLOC, YBARLOC, XBARSIZE, YBARSIZE,
						null);

				// 2b아시아가 아닐때
				g.setColor(fg);

				// 바코드 번호 입력
				Font fontsB = new Font("SansSerif", STYLE, FYBARSIZE);
				g.setFont(fontsB);

				// 16자리 이상일 경우, 잘라낸다.
				String barcode = BarcodeStr;
				if (barcode.length() >= 16)
					barcode = BarcodeStr.substring(0, 16);

				g.drawString(barcode, FXBARLOC, FYBARLOC);

				// 유효기간 생성
				Font fonts = new Font("SansSerif", Font.PLAIN, FSIZE);
				g.setFont(fonts);

				if (!ValSday.equals("") && !ValEday.equals("")) {
					g.setColor(bg);
					g.fillRect(0, 0, XSIZE, FWIDTH);

					g.setColor(fg);
					
					// 오크밸리의 경우 유효기간을 폐장일까지로 표기한다.
					if (MembId.equals("2b_oakvalley")) {
						if(!Name.equals("")) {
							Name = Name + "  ";
						}
						
						g.drawString(Name + "유효기간 : " + ValSday + " ~ " + "폐장일까지", FXLOC,
								FYLOC);
					} else {
						g.drawString("유효기간 : " + ValSday + "~" + ValEday, FXLOC, FYLOC);
					}
				}
				else if (!Name.equals("")) {
					g.setColor(bg);
					g.fillRect(0, 0, XSIZE, FWIDTH);

					g.setColor(fg);
					g.drawString(Name, FXLOC, FYLOC);
				}
			} else {
				// 2b아시아 일때

				Color as = new Color(Integer.parseInt("000000", 16));// 바코드 글자 색
				g.setColor(as);

				// 바코드 번호 입력
				Font fontsB = new Font("SansSerif", STYLE, FYBARSIZE);
				g.setFont(fontsB);
				g.drawString(BarcodeStr, FXBARLOC, FYBARLOC);

				// 유효기간 생성
				Font fonts = new Font("SansSerif", STYLE, FSIZE);
				g.setFont(fonts);
				g.drawString(Name, FXLOC, FYLOC);

			}

			BufferedImage LevelImage = LevelImage();
			if (LevelImage != null) {
				// g.drawImage(LevelImage, XBARLOC,YBARLOC,XBARSIZE,YBARSIZE,
				// null);
			}

			g.dispose();
			ImageIO.write(image, "png", isFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void setBarcodeStr(){
		if (MembId.equals("my_asia") && this.isCoupon == false) {
			BarcodeStr = "OZ " + BarcodeStr;
			if (!ValEday.equals(""))
				BarcodeStr += "  valid thru " + ValEday;
		}
	}

	private BufferedImage LevelImage() {
		BufferedImage isImage = null;
		File isFile = null;
		try {
			if (MembId.equals("olleh")) {

				if (CardLevel.equals("Royal")) {
					isFile = new File("");
				} else if (CardLevel.equals("Super")) {
					isFile = new File("");
				} else if (CardLevel.equals("Magic")) {
					isFile = new File("");
				} else if (CardLevel.equals("Happy")) {
					isFile = new File("");
				}
				isImage = ImageIO.read(isFile);
			} else if (MembId.equals("spc")) {
				// isFile = new File("");
				// isImage = ImageIO.read(isFile);
			} else if (MembId.equals("asia")) {
				// isFile = new File("");
				// isImage = ImageIO.read(isFile);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isImage;
	}

	/**
	 * 00:없음, 01:CODE128, 02:CODE39, 03:EAN-13, 04:QR CODE
	 * */
	private File makeBarcode(String code, String filename, int i) {
		if (BarType.equals("02")) {
			return CODE39(code, filename, i);

		// }else if(BarType.equals("03")){
		// return EAN13(code, filename, i);
		//}else if(BarType.equals("04")){
		//	return QRCODE(code, filename, i);
		}
		return CODE128(code, filename, i);
	}

	private File CODE128(String code, String filename, int i) {
		File outputFile = null;
		try {
			/*
			InputStream is = getClass().getResourceAsStream("/resources/barcode.properties");
			Properties props = new Properties();
			props.load(is);

			TotalPath = props.getProperty("project_path") + props.getProperty("detail");
			Host = props.getProperty("host");
			MemberPath = props.getProperty("member_path");
			CouponPath = props.getProperty("coupon_path");
			String Path = TotalPath + "/";
			*/
			String Path = "";
			Host = PropertiesUtil.get("img_host");
			MemberPath = PropertiesUtil.get("bar_member_path");
			CouponPath = PropertiesUtil.get("bar_coupon_path");

			if (i == 1) {
				//Path = TotalPath + MemberPath;
				Path = MemberPath;
				BarcodePath = MemberPath + filename;
			} else {
				//Path = TotalPath + CouponPath;
				Path = CouponPath;
				BarcodePath = CouponPath + filename;
			}

			int dpi = 240;

			Code128Bean bean = new Code128Bean();
			bean.setModuleWidth(UnitConv.in2mm(2.0f / dpi)); // 큼
			bean.doQuietZone(false);
			//bean.setFontSize(4);
			bean.setFontSize(0.0);

			FullPath = Path + filename;

			bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
			outputFile = new File(FullPath);

			OutputStream out = new FileOutputStream(outputFile);
			try {
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,"image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

				// Generate the barcode
				bean.generateBarcode(canvas, code);
				canvas.finish();

			} finally {
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFile;
	}

	private File CODE39(String code, String filename, int i) {
		File outputFile = null;
		try {
			/*
			InputStream is = getClass().getResourceAsStream("/resources/barcode.properties");
			Properties props = new Properties();
			props.load(is);

			TotalPath = props.getProperty("project_path")+ props.getProperty("detail");
			Host = props.getProperty("host");
			MemberPath = props.getProperty("member_path");
			CouponPath = props.getProperty("coupon_path");
			String Path = TotalPath + "/";
			*/
			TotalPath = "";
			String Path = "";
			Host = PropertiesUtil.get("img_host");
			MemberPath = PropertiesUtil.get("bar_member_path");
			CouponPath = PropertiesUtil.get("bar_coupon_path");

			if (i == 1) {
				//Path = TotalPath + MemberPath;
				Path = MemberPath;
				BarcodePath = MemberPath + filename;
			} else {
				//Path = TotalPath + CouponPath;
				Path =  CouponPath;
				BarcodePath = CouponPath + filename;
			}

			int dpi = 100;

			Code39Bean bean = new Code39Bean();
			bean.setModuleWidth(UnitConv.in2mm(1.65f / dpi)); // 큼
			bean.setFontSize(4);

			FullPath = Path + filename;

			// FullPath = "C:/data/" + filename;
			// 바코드 관련 설정
			bean.doQuietZone(false);
			bean.setHeight(25);
			bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
			outputFile = new File(FullPath);

			OutputStream out = new FileOutputStream(outputFile);
			try {
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

				// Generate the barcode
				bean.generateBarcode(canvas, code);
				canvas.finish();

			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFile;
	}

	private File EAN13(String code, String filename, int i) {
		File outputFile = null;
		try {
			/*
			InputStream is = getClass().getResourceAsStream("/resources/barcode.properties");
			Properties props = new Properties();
			props.load(is);

			TotalPath = props.getProperty("project_path") + props.getProperty("detail");
			Host = props.getProperty("host");
			MemberPath = props.getProperty("member_path");
			CouponPath = props.getProperty("coupon_path");
			String Path = TotalPath + "/";
			*/

			TotalPath = "";
			String Path = "";
			Host = PropertiesUtil.get("img_host");
			MemberPath = PropertiesUtil.get("bar_member_path");
			CouponPath = PropertiesUtil.get("bar_coupon_path");

			if (i == 1) {
				//Path = TotalPath + MemberPath;
				Path = MemberPath;
				BarcodePath = MemberPath + filename;
			} else {
				//Path = TotalPath + CouponPath;
				Path = CouponPath;
				BarcodePath = CouponPath + filename;
			}

			int dpi = 100;

			EAN13Bean bean = new EAN13Bean();
			bean.setModuleWidth(UnitConv.in2mm(1.65f / dpi)); // 큼
			bean.setFontSize(4);

			FullPath = Path + filename;

			// FullPath = "C:/data/" + filename;
			// 바코드 관련 설정
			bean.doQuietZone(false);
			bean.setHeight(25);
			bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
			outputFile = new File(FullPath);

			OutputStream out = new FileOutputStream(outputFile);
			try {
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,
						"image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY,
						false, 0);

				// Generate the barcode
				bean.generateBarcode(canvas, code);
				canvas.finish();

			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFile;
	}

	private File QRCODE(String code, String filename, int i) {
		File outputFile = null;
		try {
			/*
			InputStream is = getClass().getResourceAsStream("/resources/barcode.properties");
			Properties props = new Properties();
			props.load(is);

			TotalPath = props.getProperty("project_path") + props.getProperty("detail");
			Host = props.getProperty("host");
			MemberPath = props.getProperty("member_path");
			CouponPath = props.getProperty("coupon_path");
			String Path = TotalPath + "/";
			*/

			TotalPath = "";
			String Path = "";
			Host = PropertiesUtil.get("img_host");
			MemberPath = PropertiesUtil.get("bar_member_path");
			CouponPath = PropertiesUtil.get("bar_coupon_path");

			if (i == 1) {
				//Path = TotalPath + MemberPath;
				Path = MemberPath;
				BarcodePath = MemberPath + filename;
			} else {
				//Path = TotalPath + CouponPath;
				Path = CouponPath;
				BarcodePath = CouponPath + filename;
			}

			int dpi = 100;

			EAN13Bean bean = new EAN13Bean();
			bean.setModuleWidth(UnitConv.in2mm(1.65f / dpi)); // 큼
			bean.setFontSize(4);

			FullPath = Path + filename;

			// FullPath = "C:/data/" + filename;
			// 바코드 관련 설정
			bean.doQuietZone(false);
			bean.setHeight(25);
			bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
			outputFile = new File(FullPath);

			OutputStream out = new FileOutputStream(outputFile);
			try {
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,
						"image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY,
						false, 0);

				// Generate the barcode
				bean.generateBarcode(canvas, code);
				canvas.finish();

			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFile;
	}

	private String getDeviceWdith(String userId){
		String width = "";
		CouponService cs = new CouponService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		params.put("userId", userId);
		width = cs.getDeviceWidth(params); //-- get device width

		if (width == null)
			width = "480";

		if (width.equals(""))
			width = "480";

		return width;
	}

	private int getDeviceHeight(int width) {
		int height = 0;

		if (width == 320)
			height = 480;
		else if (width == 540)
			height = 960;
		else if (width == 720 || width == 800)
			height = 1280;
		else if (width == 768)
			height = 1024;
		else
			height = 800;

		return height;
	}

	// small barcode image ratio
	private double s_w = 0.76875;
	private double s_h = 0.16875;
	private double s_barcode_x = 0.041666667;
	private double s_barcode_w = 0.685416667;
	private double s_barcode_h = 0.125;
	private double s_barcode_font_w = 0.0375;
	private double s_barcode_asia_font_size = 0.0375;
	private double s_barcode_asia_font_y = 0.0625;
	private double s_barcode_asia_code_font_size = 0.045;
	private double s_barcode_asia_code_font_y = 0.125;
	private double s_barcode_eday_font_size = 0.025;
	private double s_barcode_eday_x = 0.047916667;
	private double s_barcode_eday_y = 0.03125;
	private double s_barcode_code_font_size = 0.0375;
	private double s_barcode_code_y = 0.16;

	// large barcode image ratio
	private double l_w = 0.8025;
	private double l_h = 0.1775;
	private double l_barcode_x = 0.05;
	private double l_barcode_w = 0.7025;
	private double l_barcode_h = 0.1375;
	private double l_barcode_font_w = 0.0425;
	private double l_barcode_asia_font_size = 0.0425;
	private double l_barcode_asia_font_y = 0.06125;
	private double l_barcode_asia_code_font_size = 0.0425;
	private double l_barcode_asia_code_font_y = 0.13875;
	private double l_barcode_eday_font_size = 0.025;
	private double l_barcode_eday_x = 0.0625;
	private double l_barcode_eday_y = 0.025;
	private double l_barcode_code_font_size =  0.04;
	private double l_barcode_code_y = 0.177;

	private void setRatioSmaillBarcode(boolean big, String code_number) {
		int barcodeWidth = 0;
		int w = 0;
		int h =  0;
		int barcode_x = 0;
		int barcode_w = 0;
		int barcode_h = 0;
		int barcode_font_w = 0;
		int barcode_asia_font_size = 0;
		int barcode_asia_font_y = 0;
		int barcode_asia_code_font_size = 0;
		int barcode_asia_code_font_y =  0;
		int barcode_eday_font_size = 0;
		int barcode_eday_x = 0;
		int barcode_eday_y = 0;
		int barcode_code_font_size = 0;
		int barcode_code_y = 0;

		String width = getDeviceWdith(UserID);
		if (width != null)
			barcodeWidth = Integer.parseInt(width);
		else
			barcodeWidth = 480;

		if (big == true) {

			// chage width
			barcodeWidth = getDeviceHeight(barcodeWidth);

			w = (int) (barcodeWidth * l_w);
			h = (int) (barcodeWidth * l_h);
			barcode_x = (int) (barcodeWidth * l_barcode_x);
			barcode_w = (int) (barcodeWidth * l_barcode_w);
			barcode_h = (int) (barcodeWidth * l_barcode_h);
			barcode_font_w = (int) (barcodeWidth * l_barcode_font_w);
			barcode_asia_font_size = (int) (barcodeWidth * l_barcode_asia_font_size);
			barcode_asia_font_y = (int) (barcodeWidth * l_barcode_asia_font_y);
			barcode_asia_code_font_size = (int) (barcodeWidth * l_barcode_asia_code_font_size);
			barcode_asia_code_font_y = (int) (barcodeWidth * l_barcode_asia_code_font_y);
			barcode_eday_font_size = (int) (barcodeWidth * l_barcode_eday_font_size);
			barcode_eday_x = (int) (barcodeWidth * l_barcode_eday_x);
			barcode_eday_y = (int) (barcodeWidth * l_barcode_eday_y);
			barcode_code_font_size = (int) (barcodeWidth * l_barcode_code_font_size);
			barcode_code_y = (int) (barcodeWidth * l_barcode_code_y);
		} else {
			w = (int) (barcodeWidth * s_w);
			h = (int) (barcodeWidth * s_h);
			barcode_x = (int) (barcodeWidth * s_barcode_x);
			barcode_w = (int) (barcodeWidth * s_barcode_w);
			barcode_h = (int) (barcodeWidth * s_barcode_h);
			barcode_font_w = (int) (barcodeWidth * s_barcode_font_w);
			barcode_asia_font_size = (int) (barcodeWidth * s_barcode_asia_font_size);
			barcode_asia_font_y = (int) (barcodeWidth * s_barcode_asia_font_y);
			barcode_asia_code_font_size = (int) (barcodeWidth * s_barcode_asia_code_font_size);
			barcode_asia_code_font_y = (int) (barcodeWidth * s_barcode_asia_code_font_y);
			barcode_eday_font_size = (int) (barcodeWidth * s_barcode_eday_font_size);
			barcode_eday_x = (int) (barcodeWidth * s_barcode_eday_x);
			barcode_eday_y = (int) (barcodeWidth * s_barcode_eday_y);
			barcode_code_font_size = (int) (barcodeWidth * s_barcode_code_font_size);
			barcode_code_y = (int) (barcodeWidth * s_barcode_code_y);
		}

		FontRenderContext frc = new FontRenderContext(null, true, true);

		setXSIZE(w);// 전체 넓이
		setYSIZE(h);// 전체 높이
		setSTYLE(Font.PLAIN);
		setYBARLOC(0);// 바코드 Y좌표
		setXBARLOC(barcode_x);// 바코드 X좌표
		setXBARSIZE(barcode_w);// 바코드 X사이즈
		setYBARSIZE(barcode_h);// 바코드 Y사이즈
		setFWIDTH(barcode_font_w);
		if (MembId.equals("my_asia") && this.isCoupon == false) {
			
			setFSIZE(barcode_asia_font_size);// 이름 폰트 크기
			Font font = new Font("SansSerif", getSTYLE(), getFSIZE());
			Rectangle2D bounds = font.getStringBounds(Name, frc);
			int pos = (getXSIZE() - (int) bounds.getWidth()) / 2;
			setFXLOC(pos);// 이름 폰트 위치 X
			setFYLOC(barcode_asia_font_y);// 이름 폰트 위치 Y

			setFYBARSIZE(barcode_asia_code_font_size);// 바코드 String Size
			Font font_barcode = new Font("SansSerif", getSTYLE(), getFYBARSIZE());
			Rectangle2D bounds_barcode = font_barcode.getStringBounds(code_number, frc);
			int pos_barcode = (getXSIZE() - (int) bounds_barcode.getWidth()) / 2;
			setFXBARLOC(pos_barcode);// 바코드 String X 위치
			setFYBARLOC(barcode_asia_code_font_y);// 바코드 String Y 위치
		} else {
			setFSIZE(barcode_eday_font_size);// 유효기간등 이름 폰트 크기
			setFXLOC(barcode_eday_x);// 유효기간 폰트 위치 X
			setFYLOC(barcode_eday_y);// 유효기간 폰트 위치 Y
			setFYBARSIZE(barcode_code_font_size);// 바코드 String Size

			Font font = new Font("SansSerif", getSTYLE(), getFYBARSIZE());
			Rectangle2D bounds = font.getStringBounds(code_number, frc);
			int pos = (getXSIZE() - (int) bounds.getWidth()) / 2;

			setFXBARLOC(pos);// 바코드 String X 위치
			setFYBARLOC(barcode_code_y);// 바코드 String Y 위치
		}
	}


	/**
	 * @author 이경훈
	 * */
	/*
	class IsMode {
		private int XSIZE;
		private int YSIZE;
		private int FSIZE;
		private int FXLOC;
		private int FYLOC;
		private int FXBARLOC;
		private int FYBARLOC;
		private int FYBARSIZE;
		private int XBARLOC;
		private int YBARLOC;
		private int XBARSIZE;
		private int YBARSIZE;
		private int FWIDTH;
		private int STYLE;

		public int getSTYLE() {
			return STYLE;
		}

		public void setSTYLE(int sTYLE) {
			STYLE = sTYLE;
		}

		public int getFWIDTH() {
			return FWIDTH;
		}

		public void setFWIDTH(int fWIDTH) {
			FWIDTH = fWIDTH;
		}

		public int getXSIZE() {
			return XSIZE;
		}

		public void setXSIZE(int xSIZE) {
			XSIZE = xSIZE;
		}

		public int getYSIZE() {
			return YSIZE;
		}

		public void setYSIZE(int ySIZE) {
			YSIZE = ySIZE;
		}

		public int getFSIZE() {
			return FSIZE;
		}

		public void setFSIZE(int fSIZE) {
			FSIZE = fSIZE;
		}

		public int getFXLOC() {
			return FXLOC;
		}

		public void setFXLOC(int fXLOC) {
			FXLOC = fXLOC;
		}

		public int getFYLOC() {
			return FYLOC;
		}

		public void setFYLOC(int fYLOC) {
			FYLOC = fYLOC;
		}

		public int getFXBARLOC() {
			return FXBARLOC;
		}

		public void setFXBARLOC(int fXBARLOC) {
			FXBARLOC = fXBARLOC;
		}

		public int getFYBARLOC() {
			return FYBARLOC;
		}

		public void setFYBARLOC(int fYBARLOC) {
			FYBARLOC = fYBARLOC;
		}

		public int getFYBARSIZE() {
			return FYBARSIZE;
		}

		public void setFYBARSIZE(int fYBARSIZE) {
			FYBARSIZE = fYBARSIZE;
		}

		public int getXBARLOC() {
			return XBARLOC;
		}

		public void setXBARLOC(int xBARLOC) {
			XBARLOC = xBARLOC;
		}

		public int getYBARLOC() {
			return YBARLOC;
		}

		public void setYBARLOC(int yBARLOC) {
			YBARLOC = yBARLOC;
		}

		public int getXBARSIZE() {
			return XBARSIZE;
		}

		public void setXBARSIZE(int xBARSIZE) {
			XBARSIZE = xBARSIZE;
		}

		public int getYBARSIZE() {
			return YBARSIZE;
		}

		public void setYBARSIZE(int yBARSIZE) {
			YBARSIZE = yBARSIZE;
		}
	}*/
	private int getXSIZE() {
		return XSIZE;
	}

	private void setXSIZE(int xSIZE) {
		XSIZE = xSIZE;
	}

	private int getYSIZE() {
		return YSIZE;
	}

	private void setYSIZE(int ySIZE) {
		YSIZE = ySIZE;
	}

	private int getFSIZE() {
		return FSIZE;
	}

	private void setFSIZE(int fSIZE) {
		FSIZE = fSIZE;
	}

	private int getFXLOC() {
		return FXLOC;
	}

	private void setFXLOC(int fXLOC) {
		FXLOC = fXLOC;
	}

	private int getFYLOC() {
		return FYLOC;
	}

	private void setFYLOC(int fYLOC) {
		FYLOC = fYLOC;
	}

	private int getFXBARLOC() {
		return FXBARLOC;
	}

	private void setFXBARLOC(int fXBARLOC) {
		FXBARLOC = fXBARLOC;
	}

	private int getFYBARLOC() {
		return FYBARLOC;
	}

	private void setFYBARLOC(int fYBARLOC) {
		FYBARLOC = fYBARLOC;
	}

	private int getFYBARSIZE() {
		return FYBARSIZE;
	}

	private void setFYBARSIZE(int fYBARSIZE) {
		FYBARSIZE = fYBARSIZE;
	}

	private int getXBARLOC() {
		return XBARLOC;
	}

	private void setXBARLOC(int xBARLOC) {
		XBARLOC = xBARLOC;
	}

	private int getYBARLOC() {
		return YBARLOC;
	}

	private void setYBARLOC(int yBARLOC) {
		YBARLOC = yBARLOC;
	}

	private int getXBARSIZE() {
		return XBARSIZE;
	}

	private void setXBARSIZE(int xBARSIZE) {
		XBARSIZE = xBARSIZE;
	}

	private int getYBARSIZE() {
		return YBARSIZE;
	}

	private void setYBARSIZE(int yBARSIZE) {
		YBARSIZE = yBARSIZE;
	}

	private int getFWIDTH() {
		return FWIDTH;
	}

	private void setFWIDTH(int fWIDTH) {
		FWIDTH = fWIDTH;
	}

	private int getSTYLE() {
		return STYLE;
	}

	private void setSTYLE(int sTYLE) {
		STYLE = sTYLE;
	}

	public String getTotalPath() {
		return TotalPath;
	}

	public void setTotalPath(String totalPath) {
		TotalPath = totalPath;
	}

	public String getHost() {
		return Host;
	}

	public void setHost(String host) {
		Host = host;
	}

	public String getMemberPath() {
		return MemberPath;
	}

	public void setMemberPath(String memberPath) {
		MemberPath = memberPath;
	}

	public String getCouponPath() {
		return CouponPath;
	}

	public void setCouponPath(String couponPath) {
		CouponPath = couponPath;
	}

	public String getS_BarcodePath() {
		return S_BarcodePath;
	}

	public void setS_BarcodePath(String s_BarcodePath) {
		S_BarcodePath = s_BarcodePath;
	}

	public String getBarcodePath() {
		return BarcodePath;
	}

	public void setBarcodePath(String barcodePath) {
		BarcodePath = barcodePath;
	}

	public String getFullPath() {
		return FullPath;
	}

	public void setFullPath(String fullPath) {
		FullPath = fullPath;
	}

	public String getB_BarcodePath() {
		return B_BarcodePath;
	}

	public void setB_BarcodePath(String b_BarcodePath) {
		B_BarcodePath = b_BarcodePath;
	}

	public File getBFile() {
		return BFile;
	}

	public void setBFile(File bFile) {
		BFile = bFile;
	}

	public File getSFile() {
		return SFile;
	}

	public void setSFile(File sFile) {
		SFile = sFile;
	}
}
