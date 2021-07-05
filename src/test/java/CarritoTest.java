
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CarritoTest {

    ICarrito carrito;
    Producto papa;
    Producto lechuga;
    Producto aguacate;
    Producto mango;
    Producto yuca;

    public CarritoTest (){
    }

    @BeforeAll
    public static void setUpClass () throws Exception{
    }

    @AfterAll
    public static void tearDownClass () throws Exception{
    }

    @BeforeEach
    public void setUp (){
        carrito = FactoryCarrito.getCarrito();
        papa = new Producto( 25 , "papa" );
        lechuga = new Producto( 10 , "lechuga" );
        aguacate = new Producto( 50 , "aguacate" );
        mango = new Producto( 15 , "mango" );
        yuca = new Producto( 20 , "yuca" );

    }

    @AfterEach
    public void tearDown (){
        carrito.vaciar();
    }

    @Test
    public void testAgregarProductoExistente (){

        carrito.agregarProducto( papa , 20 );
        assertEquals( 20 , carrito.obtenerCantidad( "papa" ) );
        carrito.agregarProducto( papa , 2 );
        //deberían haber 22 papas
        assertEquals( 22 , carrito.obtenerCantidad( "papa" ) );

    }

    @Test
    public void testAgregarProdNuevo (){

        carrito.agregarProducto( aguacate , 2 );
        assertEquals( 2 , carrito.obtenerCantidad( "aguacate" ) );
    }

    @Test
    public void testAgregarProdDiferentes (){

        carrito.agregarProducto( mango , 2 );
        carrito.agregarProducto( yuca , 5 );
        assertEquals( 2 , carrito.obtenerCantidad( "mango" ) );
        assertEquals( 5 , carrito.obtenerCantidad( "yuca" ) );
    }

    @Test
    public void testAgregarProdSinCantidad (){

        NullPointerException nullPointerException = assertThrows( NullPointerException.class , () -> {
            carrito.agregarProducto( mango , 0 );
        } );
        assertEquals( "No se puede agregar al carrito un producto cuya cantidad es 0 o menor" , nullPointerException.getMessage() );

    }
    @Test
    public void testAdicionarCantnegativaExistente (){

        NullPointerException nullPointerException = assertThrows( NullPointerException.class , () -> {
            carrito.agregarProducto( papa , 20 );
            carrito.agregarProducto( papa , -5 );

        } );
        assertEquals( "No se puede agregar al carrito un producto cuya cantidad es menor a 0" , nullPointerException.getMessage() );

    }
    @Test
    public void testAgregarProdCantNegativa (){

        NullPointerException nullPointerException = assertThrows( NullPointerException.class , () -> {
            carrito.agregarProducto( mango , -5 );
        } );
        assertEquals( "No se puede agregar al carrito un producto cuya cantidad es 0 o menor" , nullPointerException.getMessage() );

    }

    @Test
    public void testObtenerCantidadProdNoExistente (){
        assertEquals( -1 , carrito.obtenerCantidad( "aguacate" ) );

    }

    @Test
    public void testProbarTotal (){
        carrito.agregarProducto( lechuga , 1 );
        carrito.agregarProducto( papa , 1 );
        assertEquals( 25 + 10 , (long) carrito.obtenerPrecioTotal() );
    }

    @Test
    public void testPTotalCarritoSinProductos (){
        assertEquals( 0.0 , carrito.obtenerPrecioTotal() );
    }

    @Test
    public void testPTotalCarritoCantMayorUno (){
        carrito.agregarProducto( aguacate , 5 );
        assertEquals( 250.00 , carrito.obtenerPrecioTotal() );
    }

    @Test
    public void testPTotalCarritoMasProdMayUno (){
        carrito.agregarProducto( aguacate , 5 );
        carrito.agregarProducto( mango , 3 );
        assertEquals( 295.00 , carrito.obtenerPrecioTotal() );
    }




}