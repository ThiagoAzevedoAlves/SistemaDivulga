package sistemadivulga.bematech;

import com.sun.jna.ptr.IntByReference;
import sistemadivulga.bematech.BematechNFiscal;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int iRetorno;
		int iOpc = -1;
		
		BematechNFiscal cupom = BematechNFiscal.Instance;

		while (iOpc != 0)
		{
		
			System.out.println("Elija una opcion:" + "\n");
			System.out.println("<1> Imprime Texto");
			System.out.println("<2> Imprime Bitmap");
			System.out.println("<0> Finalizar");
			
			iOpc = Console.readInt( "\nOpcion -> ");		
			
			switch (iOpc)
			{
				case 1:
					iRetorno = cupom.ConfiguraModeloImpressora(7);
					iRetorno = cupom.IniciaPorta("USB");
					iRetorno = cupom.FormataTX("TESTE DE IMPRESSAO\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FechaPorta();
				break;	
				case 2:
					iRetorno = cupom.ConfiguraModeloImpressora(7);
					iRetorno = cupom.IniciaPorta("USB001");
					iRetorno = cupom.ImprimeBitmap("C:\\Temp\\IMAGEM.BMP", 0);
					iRetorno = cupom.FechaPorta();
				break;
					
			}
		}
		
	}

}
