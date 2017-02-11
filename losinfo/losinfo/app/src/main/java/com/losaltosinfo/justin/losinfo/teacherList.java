package com.losaltosinfo.justin.losinfo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
//import com.google.common.ImmutableList;



import java.util.Arrays;
import java.util.Map;

import static com.losaltosinfo.justin.losinfo.R.id.textView;


public class teacherList extends AppCompatActivity {

    //private String[] teachers = {"Mars", "earth"};
    //private String[] teacherEmail = {"email", "email2"};
   // private Map<String, String> teacherEmails = ImmutableMap.of("Mars", "email", "earth", "email2");

    private String subject;
    private String[] staffList = {
            "Carrie Abel"
            ,"Chad Ablang"
            ,"Martin Acosta"
            ,"Dafna Adler"
            ,"Bianca Aguirre"
            ,"Rudy Alcala"
            ,"Silvia Alcala"
            ,"Sarah Alvarado"
            ,"Jade Alvarez"
            ,"Lauren Amoros"
            ,"Christine An"
            ,"Adam Anderson"
            ,"Lilia Apolinar"
            ,"Aranxta Arriada"
            ,"Robert Barker"
            ,"Christophe Barquissau"
            ,"Linda Baxley"
            ,"Margaret Bennett"
            ,"Martha Bills"
            ,"Brenda Birrell"
            ,"Michelle Bissonnette"
            ,"Lisa Bonanno"
            ,"Adriana Bonilla"
            ,"Daniel Borklund"
            ,"Christine Bridges"
            ,"Carlos Camplis"
            ,"Lisa Cardellini"
            ,"Sarah Carlson"
            ,"Richard Carmona"
            ,"Patrick Carras"
            ,"Ryan Carter"
            ,"Dan Carter"
            ,"Charles Castleman"
            ,"Kim Cave"
            ,"Matthew Chaffee"
            ,"Felipe Chavez"
            ,"Connie Chen"
            ,"Adrian Cheng"
            ,"Freedom Cheteni"
            ,"Vickie Christensen"
            ,"Abigail Christensen"
            ,"Kelly Coble"
            ,"Antonio Coelho"
            ,"Julie Corzine"
            ,"Georgina Davila"
            ,"Karen Davis"
            ,"Cristy Dawson"
            ,"Keren Dawson-Bowman"
            ,"Kalia DeMarquez"
            ,"Chelsea Doiguchi"
            ,"Mary Donahue"
            ,"Seth Donnelly"
            ,"Stephanie Downey"
            ,"Darren Dressen"
            ,"Teresa Dunlap"
            ,"Bryan Edwards"
            ,"Greg Ely"
            ,"Rick Esparza"
            ,"Carol Evans"
            ,"Patty Fambrini"
            ,"Ted Ferrucci"
            ,"Robert Freeman"
            ,"Susan Friedeberg"
            ,"April Fritz"
            ,"Heidi Frost"
            ,"Joey Fuentes"
            ,"Stephanie Fullen-Safian"
            ,"Elizabeth Gabriel"
            ,"Heidi Galvez"
            ,"Christine Garcia"
            ,"Fernanda Garcia"
            ,"Gina Garcia-Smith"
            ,"James Gibbs"
            ,"James Godfrey"
            ,"Jillian Green"
            ,"Krista Greksouk"
            ,"Hortensia Halsted"
            ,"Kirsten Haney"
            ,"Kim Hanley"
            ,"Caitlin Hannon"
            ,"Joshua Harmon"
            ,"Brent Hatakeyama"
            ,"Jessica Hayes"
            ,"Pete Hernandez"
            ,"Susana Herrera"
            ,"Claudia Hevel"
            ,"Stephen Hine"
            ,"Joshua Hinrichs"
            ,"Maria Hoerni"
            ,"Robyn Hughes"
            ,"John Humphrey"
            ,"Laraine Ignacio"
            ,"Lori Ilano"
            ,"Gordon Jack"
            ,"Michael Kanda"
            ,"Rosemary Kaplan"
            ,"Tiffany Karow"
            ,"Jonathan Kwan"
            ,"Merlin Kwon"
            ,"LaVerne Lacey"
            ,"Albert Lagazo"
            ,"Jacob Larin"
            ,"Elizabeth Latona"
            ,"Cariann Lee"
            ,"Alice Lee"
            ,"Trina Lee"
            ,"Eunice Lee"
            ,"Marc Leone"
            ,"Stefaan Lodge"
            ,"Linda Lorimor"
            ,"Kathryn Low"
            ,"Rainie Maciel"
            ,"Jasmine Mark"
            ,"Bob McFarlane"
            ,"Karen McHugh"
            ,"Mike Messner"
            ,"Alise Miller"
            ,"Don Miller"
            ,"Deanna Mistele"
            ,"Derek Miyahara"
            ,"Joanne Miyahara"
            ,"Nancy Moran"
            ,"Kao Moua"
            ,"Michael Moul"
            ,"Antonio Murillo"
            ,"Hannah Nguyen"
            ,"Daniel Nguyen"
            ,"Quyen Nguyen"
            ,"Megan Nijor"
            ,"Lisa Nishiura"
            ,"Amy O'Hayer"
            ,"Lucas Okuma"
            ,"Nansi Olguin"
            ,"April Oliver"
            ,"Maria Olson"
            ,"Gerardo Ortega"
            ,"Ignacio Ortega"
            ,"Lina Padilla"
            ,"Danielle Paige"
            ,"Jeamice Parker"
            ,"Perla Pasallo"
            ,"Jim Phillips"
            ,"Chris Phipps"
            ,"Kalinda Price"
            ,"Judy Prothro"
            ,"Kiernan Raffo"
            ,"Adam Randall"
            ,"Bob Randall"
            ,"Lindsey Regoli"
            ,"Michael Richardson"
            ,"Keren Robertson"
            ,"Ariel Rojas"
            ,"Galen Rosenberg"
            ,"Myles Rowland"
            ,"Richard Rullo"
            ,"Jacob Russo"
            ,"Angel Sabangan"
            ,"Terri Salsman De Rodriguex"
            ,"Alexandra Sanchez"
            ,"Adriana Sanchez Hurtado"
            ,"Jay Santiago"
            ,"Manju Sardana"
            ,"Wynne Satterwhite"
            ,"Amy Schlueter"
            ,"Christina Schramm"
            ,"Jeanine Seagraves"
            ,"Craig Seran"
            ,"Mark Shaull"
            ,"Willie Sims"
            ,"Maryann Smetzer"
            ,"Michael Smith"
            ,"Abigale Smith"
            ,"Bobby Soto"
            ,"Danny Spiteri"
            ,"Erica Starks"
            ,"Gabriel Stewart"
            ,"Greg Stoehr"
            ,"Judy Strauss"
            ,"Meghan Strazicich"
            ,"Kelly Sumner"
            ,"Dayana Swank"
            ,"Elizabeth Tompkins"
            ,"Nicole Tsai"
            ,"Lilian Valdivieso"
            ,"Cristina Valqui"
            ,"Antonio Ventura"
            ,"Zachary Waldorph"
            ,"Todd Wangsness"
            ,"Christa Wemmer"
            ,"Shannon Wernette"
            ,"Conor White"
            ,"Dwayne Witherspoon"
            ,"June Wong"
            ,"Suzanne Woolfolk"
            ,"Amy Yaeger"
            ,"Betty Yamasaki"
            ,"Ia Yang"
            ,"Eileen Yen"
            ,"Jeanne Yu"
};

    private String[] staffEmailList = {
            "Carrie.Abel@mvla.net"
            , "Chad.Ablang@mvla.net"
            , "Martin.Acosta@mvla.net"
            , "Dafna.Adler@mvla.net"
            , "Bianca.Aguirre@mvla.net"
            , "Rudy.Alcala@mvla.net"
            , "Silvia.Alcala@mvla.net"
            , "Sarah.Alvarado@mvla.net"
            , "Jade.Alvarez@mvla.net"
            , "Lauren.Amoros@mvla.net"
            , "Christine.An@mvla.net"
            , "Adam.Anderson@mvla.net"
            , "Lilia.Apolinar@mvla.net"
            , "Arantxa.Arriada@mvla.net"
            , "robert.barker@mvla.net"
            , "Christophe.Barquissau@mvla.net"
            , "baxleyla@me.com"
            , "Margaret.Bennett@mvla.net"
            , "Martha.Bills@mvla.net"
            , "Brenda.Birrell@mvla.net"
            , "Michelle.Bissonnette@mvla.net"
            , "Lisa.Bonanno@mvla.net"
            , "Adriana.Bonilla@mvla.net"
            , "Daniel.Borklund@mvla.net"
            , "Christine.Bridges@mvla.net"
            , "Carlos.Camplis@mvla.net"
            , "Lisa.Cardellini@mvla.net"
            , "Sarah.Carlson@mvla.net"
            , "Richard.Carmona@mvla.net"
            , "Patrick.Carras@mvla.net"
            , "Ryan.Carter@mvla.net"
            , "Dan.Carter@mvla.net"
            , "Charles.Castleman@mvla.net"
            , "Kim.Cave@mvla.net"
            , "Matthew.Chaffee@mvla.net"
            , "Felipe.Chavez@mvla.net"
            , "Connie.Chen@mvla.net"
            , "Adrian.Cheng@mvla.net"
            , "Freedom.Cheteni@mvla.net"
            , "Vickie.Christensen@mvla.net"
            , "Abigail.Christensen@mvla.net"
            , "Kelly.Coble@mvla.net"
            , "Antonio.Coelho@mvla.net"
            , "Julie.Corzine@mvla.net"
            , "Georgina.Davila@mvla.net"
            , "Karen.Davis@mvla.net"
            , "Cristy.Dawson@mvla.net"
            , "Keren.Dawson­Bowman@mvla.net"
            , "Kalia.DeMarquez@mvla.net"
            , "Chelsea.Doiguchi@mvla.net"
            , "Mary.Donahue@mvla.net"
            , "Seth.Donnelly@mvla.net"
            , "Stephanie.Downey@mvla.net"
            , "Darren.Dressen@mvla.net"
            , "teresa.dunlap@mvla.net"
            , "bryan.edwards@mvla.net"
            , "Greg.Ely@mvla.net"
            , "Rick.Esparza@mvla.net"
            , "Carol.Evans@mvla.net"
            , "Patty.Fambrini@mvla.net"
            , "Ted.Ferrucci@mvla.net"
            , "Robert.Freeman@mvla.net"
            , "Susan.Friedeberg@mvla.net"
            , "April.Fritz@mvla.net"
            , "Heidi.Frost@mvla.net"
            , "Joey.Fuentes@mvla.net"
            , "stephanie.fullen­safian@mvla.net"
            , "Elizabeth.Gabriel@mvla.net"
            , "Heidi.Galvez@mvla.net"
            , "Christine.Garcia@mvla.net"
            , "fernanda.garcia@mvla.net"
            , "gina.garcia­smith@mvla.net"
            , "James.Gibbs@mvla.net"
            , "James.Godfrey@mvla.net"
            , "Jillian.Green@mvla.net"
            , "Krista.Greksouk@mvla.net"
            , "Hortensia.Halsted@mvla.net"
            , "Kirsten.Haney@mvla.net"
            , "Kim.Hanley@mvla.net"
            , "Caitlin.Hannon@mvla.net"
            , "joshua.harmon@mvla.net"
            , "Brent.Hatakeyama@mvla.net"
            , "Jessica.Hayes@mvla.net"
            , "Pete.Hernandez@mvla.net"
            , "Susana.Herrera@mvla.net"
            , "Claudia.Hevel@mvla.net"
            , "Stephen.Hine@mvla.net"
            , "Joshua.Hinrichs@mvla.net"
            , "Maria.Hoerni@mvla.net"
            , "Robyn.Hughes@mvla.net"
            , "John.Humphrey@mvla.net"
            , "Laraine.Ignacio@mvla.net"
            , "Lori.Ilano@mvla.net"
            , "Gordon.Jack@mvla.net"
            , "Michael.Kanda@mvla.net"
            , "Rosemary.Kaplan@mvla.net"
            , "Tiffany.Karow@mvla.net"
            , "Jonathan.Kwan@mvla.net"
            , "Merlin.Kwon@mvla.net"
            , "LaVerne.Lacey@mvla.net"
            , "albert.lagazo@mvla.net"
            , "Jacob.Larin@mvla.net"
            , "Elizabeth.Latona@mvla.net"
            , "Cariann.Lee@mvla.net"
            , "Alice.Lee@mvla.net"
            , "Trina.Lee@mvla.net"
            , "eunice.lee@mvla.net"
            , "marc.leone@mvla.net"
            , "Stefaan.Lodge@mvla.net"
            , "Linda.Lorimor@mvla.net"
            , "kathryn.low@mvla.net"
            , "Rainie.Maciel@mvla.net"
            , "jasmine.mark@mvla.net"
            , "Bob.McFarlane@mvla.net"
            , "Karen.McHugh@mvla.net"
            , "Mike.Messner@mvla.net"
            , "Alise.Miller@mvla.net"
            , "Don.Miller@mvla.net"
            , "deanna.salley­mistele@mvla.net"
            , "Derek.Miyahara@mvla.net"
            , "Joanne.Miyahara@mvla.net"
            , "Nancy.Moran@mvla.net"
            , "Kao.Moua@mvla.net"
            , "Michael.Moul@mvla.net"
            , "Antonio.Murillo@mvla.net"
            , "Hannah.Nguyen@mvla.net"
            , "Daniel.Nguyen@mvla.net"
            , "quyen.nguyen@mvla.net"
            , "megan.nijor@mvla.net"
            , "Lisa.Nishiura@mvla.net"
            , "Amy.OHayer@mvla.net"
            , "lucas.okuma@mvla.net"
            , "nansi.olguin@mvla.net"
            , "April.Oliver@mvla.net"
            , "maria.olson@mvla.net"
            , "Gerardo.Ortega@mvla.net"
            , "Ignacio.Ortega@mvla.net"
            , "Lina.Padilla@mvla.net"
            , "Danielle.Paige@mvla.net"
            , "Jeamice.Parker@mvla.net"
            , "Perla.Pasallo@mvla.net"
            , "jim.phillips@mvla.net"
            , "Chris.Phipps@mvla.net"
            , "Kalinda.Price@mvla.net"
            , "Judy.Prothro@mvla.net"
            , "Kiernan.Raffo@mvla.net"
            , "Adam.Randall@mvla.net"
            , "Bob.Randall@mvla.net"
            , "Lindsey.Regoli@mvla.net"
            , "Michael.Richardson@mvla.net"
            , "Keren.Robertson@mvla.net"
            , "Ariel.Rojas@mvla.net"
            , "Galen.Rosenberg@mvla.net"
            , "Myles.Rowland@mvla.net"
            , "Richard.Rullo@mvla.net"
            , "Jacob.Russo@mvla.net"
            , "Angel.Sabangan@mvla.net"
            , "Terri.SalsmanDeRodriguex@mvla.net"
            , "Alexandra.Sanchez@mvla.net"
            , "adriana.sanchezhurtado@mvla.net"
            , "Jay.Santiago@mvla.net"
            , "manju.sardana@mvla.net"
            , "Wynne.Satterwhite@mvla.net"
            , "Amy.Schlueter@mvla.net"
            , "Christina.Schramm@mvla.net"
            , "Jeanine.Seagraves@mvla.net"
            , "Craig.Seran@mvla.net"
            , "Mark.Shaull@mvla.net"
            , "willie.sims@mvla.net"
            , "Maryann.Smetzer@mvla.net"
            , "Michael.Smith@mvla.net"
            , "test@aol.com"
            , "bobby.soto@mvla.net"
            , "Danny.Spiteri@mvla.net"
            , "Erica.Starks@mvla.net"
            , "Gabriel.Stewart@mvla.net"
            , "Greg.Stoehr@mvla.net"
            , "Judy.Strauss@mvla.net"
            , "meghan.strazicich@mvla.net"
            , "Kelly.Sumner@mvla.net"
            , "Dayana.Swank@mvla.net"
            , "Elizabeth.Tompkins@mvla.net"
            , "Nicole.Tsai@mvla.net"
            , "Lilian.Valdivieso@mvla.net"
            , "Cristina.Valqui@mvla.net"
            , "Antonio.Ventura@mvla.net"
            , "Zachary.Waldorph@mvla.net"
            , "Todd.Wangsness@mvla.net"
            , "Christa.Wemmer@mvla.net"
            , "Shannon.Wernette@mvla.net"
            , "Conor.White@mvla.net"
            , "Dwayne.Witherspoon@mvla.net"
            , "June.Wong@mvla.net"
            , "Suzanne.Woolfolk@mvla.net"
            , "Amy.Yaeger@mvla.net"
            , "Betty.Yamasaki@mvla.net"
            , "Ia.Yang@mvla.net"
            , "Eileen.Yen@mvla.net"
            , "Jeanne.Yu@mvla.net"
    };


    SearchView sv;
    public String emailTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        populateListView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailTo == null || emailTo == "") {
                    Snackbar snackbar = Snackbar.make(view, "Select staff member for email first!", Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    sbView.setBackgroundColor(Color.rgb(9, 49, 69));
                    snackbar.show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailTo });
                subject = "Dear " + emailTo + ",";
                intent.putExtra(Intent.EXTRA_SUBJECT, new String[] { subject });

                if( intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                    emailTo = null;
                }

            }
        });
    }

    private void populateListView() {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        //setContentView(R.layout.content_teacher_list);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.teacher_list_item, R.id.name, staffList);
        sv = (SearchView) findViewById(R.id.searchView);
        sv.setSubmitButtonEnabled(false);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        GridView grid = (GridView) findViewById(R.id.gridView);
        //sv.setIconified(true);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String teacher = adapter.getItem(position);
                int index = Arrays.asList(staffList).indexOf(teacher);
                Snackbar snackbar = Snackbar.make(view, "Press button to send email to: " + staffEmailList[index], Snackbar.LENGTH_LONG);
                emailTo = staffEmailList[index];
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                sbView.setBackgroundColor(Color.rgb(9, 49, 69));
                snackbar.show();
            }
        });

    }



}
