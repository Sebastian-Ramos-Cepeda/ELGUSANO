package es.studium.ElGusano;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Point;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;


public class ElGusano extends JFrame implements WindowListener 
{
	private static final long serialVersionUID = 1L;
	Graficos graficos;
    Point gusano;
    Point manzanas;
    ArrayList<Point> contador = new ArrayList<Point>();

    int longitud = 2;

    int ancho = 640;
    int alto = 480;

    int anchoGusano = 10;
    int altoGusano = 10;

	String direccion = "RIGHT";
	long velocidad = 90;

    boolean fin = false;

    public ElGusano() 
    {
		setTitle("Juego 'El Gusano'");

        inicio();
        graficos = new Graficos();

        this.getContentPane().add(graficos);

		setSize(ancho,alto);
		
		this.addKeyListener(new Teclas());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setResizable(false);
		setLocationRelativeTo(null);
        setVisible(true);
        
        Movimiento movimiento = new Movimiento();
		Thread trid = new Thread(movimiento);
		trid.start();
    }

    //Inicio del juego
    public void inicio() 
    {
		manzanas = new Point(200,100);	
        gusano = new Point(320,240);
		contador = new ArrayList<Point>();
        contador.add(gusano);
		longitud = contador.size();        
    }

    //Aparicones aleatorias de las manzanas.
    //Corrección de las posiciones aleatorias de las manazanas, que aparecen fuera de la ventana.
	public void aparicionManzanas() 
	{
		Random rnd = new Random();
		
		manzanas.x = (rnd.nextInt(ancho)) + 5;
		
		if((manzanas.x % 5) > 0) 
		{
			manzanas.x = manzanas.x - (manzanas.x % 5);
		}

		if(manzanas.x < 5) 
		{
			manzanas.x = manzanas.x + 10;
		}
		
		if(manzanas.x > ancho) 
		{
			manzanas.x = manzanas.x - 10;
		}

		manzanas.y = (rnd.nextInt(alto)) + 5;
		
		if((manzanas.y % 5) > 0) 
		{
			manzanas.y = manzanas.y - (manzanas.y % 5);
		}	

		if(manzanas.y > alto) 
		{
			manzanas.y = manzanas.y - 10;
		}
		
		if(manzanas.y < 0) 
		{
			manzanas.y = manzanas.y + 10;
		}

	}

	public void actualizar() 
	{

        contador.add(0,new Point(gusano.x,gusano.y));
		contador.remove(contador.size()-1);

		//Establezco que cuando el gusano coincida con un punto (extensión de su tamaño) en los ejes x/y, se acaba el juego
        for (int i=1;i<contador.size();i++) 
        {
            Point point = contador.get(i);
            if(gusano.x == point.x && gusano.y  == point.y)
            {
                fin = true;
            }
        }
        
        //Establecemos la zona en la coincide el gusano con la manzana, para despues llamar a Graficos y añadir cuerpo al gusano.
		if((gusano.x > (manzanas.x-10) && gusano.x < (manzanas.x+10)) && (gusano.y > (manzanas.y-10) && gusano.y < (manzanas.y+10)))
		{
            contador.add(0,new Point(gusano.x,gusano.y));
			System.out.println(contador.size());
			aparicionManzanas();
		}
        graficos.repaint();

	}

	public static void main(String[] args) 
	{
		ElGusano gusano1 = new ElGusano();
	}

    public class Graficos extends JPanel 
    {
     
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) 
		{
            super.paintComponent(g);            
            //Si termina, el fondo se pone blanco
            if(fin) 
            {
                g.setColor(Color.white);
            } 
            //Mientra juegas el fondon es oscuro
            else 
            {
                g.setColor(Color.darkGray);
            }
            g.fillRect(0,0, ancho, alto);
            //Color del gusano
            g.setColor(Color.orange);
            
            //IF para que cuando la puntuación sea mayor que 0, establezca el mismo color al tamaño extra del gusano
            if(contador.size() > 0) 
            {
                for(int i=0;i<contador.size();i++) 
                {
                	
                    Point p = (Point)contador.get(i);
                    g.fillRect(p.x,p.y,anchoGusano,altoGusano);
                }
            }
            
            //Color de las manzanas
            g.setColor(Color.red);
            g.fillRect(manzanas.x,manzanas.y,anchoGusano,altoGusano);    
            
            //Pantalla final del juego con puntuacion y opciones de reiniciar o salir
            if(fin) 
            {
                g.setFont(new Font("Action Man Extended", Font.BOLD, 40));
                g.drawString("Fin del juego", 300, 200);
                g.drawString("Puntuación: "+(contador.size()-1), 300, 240);

                g.setFont(new Font("Action Man Extended", Font.BOLD, 20));
                g.drawString("Pulsa 'N' para jugar otra partida.", 100, 320);
                g.drawString("Pulsa 'ESC' para salir del juego.", 100, 340);
            }

        }
    }
    
    
    //Funcionamiento de las teclas
	public class Teclas extends java.awt.event.KeyAdapter 
	{
		@Override
		public void keyPressed(KeyEvent e) 
		{

			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
			{
				System.exit(0);
			} 
			
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
			{

				if(direccion != "LEFT") 
				{
                    direccion = "RIGHT";

				}
			} 
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) 
			{
				if(direccion != "RIGHT") 
				{
                    direccion = "LEFT";
				}
			} 
			
			else if(e.getKeyCode() == KeyEvent.VK_UP) 
			{
				if(direccion != "DOWN") 
				{
                    direccion = "UP";
				}
			} 
			
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			{
				if(direccion != "UP") 
				{
                    direccion = "DOWN";
				}				
			
			} 
			
			else if(e.getKeyCode() == KeyEvent.VK_N) 
			{
                fin = false;
                inicio();				
			}
		}

	}

	
	//Movimiento del gusano, para que cuando salga por algun extremo aparezca por el contrario.
	public class Movimiento extends Thread 
	{
		
		private long ultima = 0;
		
		public Movimiento() {}

		public void run() 
		{
			while(true) 
			{
				if((java.lang.System.currentTimeMillis() - ultima) > velocidad) 
				{
					if(!fin) 
					{

                        if(direccion == "RIGHT") 
                        {
                            gusano.x = gusano.x + anchoGusano;
                            if(gusano.x > ancho) 
                            {
                                gusano.x = 0;
                            }
                        } 
                        
                        else if(direccion == "LEFT") 
                        {
                            gusano.x = gusano.x - anchoGusano;
                            if(gusano.x < 0)
                            {
                                gusano.x = ancho - anchoGusano;
                            }                        
                        } 
                        
                        else if(direccion == "UP") 
                        {
                            gusano.y = gusano.y - altoGusano;
                            if(gusano.y < 0) 
                            {
                                gusano.y = alto;
                            }                        
                        } 
                        
                        else if(direccion == "DOWN")
                        {
                            gusano.y = gusano.y + altoGusano;
                            if(gusano.y > alto) 
                            {
                                gusano.y = 0;
                            }                        
                        }
                    }
                    actualizar();
					
					ultima = java.lang.System.currentTimeMillis();
				}

			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}