package com.ceva;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

/**
 * Reporte que consulta millones de registros
 */
public class HibernateSQL {

    // doWork() nos permite hacer consultas JDBC, le pasamos una funcion lambda
    // doWork() pone a nuestra disposicion una conexion JDBC
    private static void doWork(Session session) {
        session.doWork((connection) -> {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id_product, name from product");
            while (rs.next())
                System.out.printf("%d\t%s\n", rs.getInt("id_product"), rs.getString("name"));
            rs.close();
            stmt.close();
        });
    }

    // hacemos una consulta de objetos tipo product
    private static void selectFromSQL(Session session) {
        List<Product> list = session.createNativeQuery(
                        "select 1000 as id_product, 'Virtual product' as name, 1 as price, '' as description from sysibm.sysdummy1")
                // el resultado lo convertimos a la entidad Product (se debe incluir todas la columns del objeto a mapear)
                .addEntity(Product.class)
                .list();
        for (Product p : list)
            System.out.printf("%d\t%s\n", p.getId_product(), p.getName());
    }

    // hacemos una consulta de informacion que no se puede representar con entidades
    // por ejem una tabla con 100 columnas pero solo necesitamos dos columns
    private static void selectFromSQL2(Session session) {
        List<Object[]> list = session.createNativeQuery("select id_product,name from product")
                .list();
        // cada elemento del array corresponde a cada columna leida
        for (Object[] p : list)
            // imprimimos id_product y name
            System.out.printf("%d\t%s\n", p[0], p[1]);
    }

    // hacemos una consulta de informacion que no se puede representar con entidades
    // e indicamos el tipo de dato de cada columna par que no se consulte al metadata deL rs
    private static void selectFromSQL3(Session session) {
        List<Object[]> list = session.createNativeQuery("select id_product,name from product")
                .addScalar("id_product", IntegerType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .list();
        for (Object[] p : list)
            System.out.printf("%d\t%s\n", p[0], p[1]);
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.newSession();
        Transaction tx = session.beginTransaction();

        //doWork(session);
        //selectFromSQL(session);
        //selectFromSQL2(session);
        selectFromSQL3(session);

        tx.commit();
        session.close();
    }
}
