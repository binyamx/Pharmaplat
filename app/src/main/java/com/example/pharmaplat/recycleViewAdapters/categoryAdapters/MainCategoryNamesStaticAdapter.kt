package com.example.pharmaplat.recycleViewAdapters.categoryAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.DataModel.SubCategoryData
import com.example.pharmaplat.R
import com.example.pharmaplat.UpdateRecyclerView
import kotlinx.android.synthetic.main.item_category_tittle.view.*

class MainCategoryNamesStaticAdapter(
    private var mainCategoryNamesList: MutableList<CategoryNames>,
    private var updateRecyclerView: UpdateRecyclerView
) :
    RecyclerView.Adapter<MainCategoryNamesStaticAdapter.CategoryNamesStaticViewHolder>() {


    var rowIndex: Int = -1
    var check: Boolean = true
    var select: Boolean = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryNamesStaticViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(
            R.layout.item_category_tittle,
            parent,
            shouldAttachToParentImmediately
        )

        return CategoryNamesStaticViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryNamesStaticViewHolder,
        position: Int
    ) {

        val item = mainCategoryNamesList[position]
        holder.bindCategoryNamesList(item)

        if (check) {

            val antacids: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Aluminum Hydroxide + Magnesium Hydroxide + Simethicone"),
                CategoryNames("Aluminum Hydroxide + Magnesium Trisilicate"),


                )
            val antiUlcer: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Cimetidine"),
                CategoryNames("Omeprazole"),
                CategoryNames("Ranitidine")

            )
            val upperGIBleeding: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Octreotide"),
                CategoryNames("Pantoprazole"),
                CategoryNames("Omeprazole"),
                CategoryNames("Propranolol")

            )
            val antiSpasmodics: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Hyoscine (Scopolamine) Butylbromide"),
                CategoryNames("Hyoscine (Scopolamine) Hydrobromide"),

                )
            val antiemetics: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Chlorpromazine Hydrochloride"),
                CategoryNames("Dimenhydrinate"),
                CategoryNames("Dexamethasone"),
                CategoryNames("Meclizine Hydrochloride + Vitamin B6"),
                CategoryNames("Metoclopramide Hydrochloride"),
                CategoryNames("Ondansetron")
            )
            val catharticsAndLaxaties: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Bisacodyl"),
                CategoryNames("Castor oil"),
                CategoryNames("Glycerin"),
                CategoryNames("Lactulose"),
                CategoryNames("Polyethylene Glycol (PEG)"),

                )
            val diarrheaManagment: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Loperamide"),
                CategoryNames("Oral Rehydration Salt"),
                CategoryNames("Zinc Sulphate"),
                CategoryNames("ReSoMal"),
                CategoryNames("Polyethylene Glycol (PEG)"),

                )
            val antiflatulents: MutableList<CategoryNames> = mutableListOf(
                CategoryNames("Simethicone")
            )
            val antihaemorrhoidal: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Betamethasone Valerate + Phenylephrine HCl + LidocaineHCl"),
                CategoryNames("Hydrocortisone + Framycetin + Cinchocaine + Esculoside"),
            )
            val iBS: MutableList<CategoryNames> = mutableListOf(

                CategoryNames("Sulfasalazine"),
                CategoryNames("Azathioprine"),
                CategoryNames("Methylprednisolone"),

                )

            val subgastroIntestinalMedicines: MutableList<SubCategoryData> = mutableListOf(
                SubCategoryData("Antacids", antacids),
                SubCategoryData("Antiulcer agents", antiUlcer),
                SubCategoryData("Upper GI bleeding Medicines", upperGIBleeding),
                SubCategoryData("Antispasmodics/ Spasmolytics analgesics", antiSpasmodics),
                SubCategoryData("Antiemetics", antiemetics),
                SubCategoryData("Laxatives and Cathartics  ", catharticsAndLaxaties),
                SubCategoryData("Diarrhea management Medicines", diarrheaManagment),
                SubCategoryData("Antiflatulents", antiflatulents),
                SubCategoryData("Antihaemorrhoidal Agents", antihaemorrhoidal),
                SubCategoryData("Inflammatory Bowel Disease Medicines", iBS)
            )

            updateRecyclerView.callback(position, subgastroIntestinalMedicines)

            check = false
        }

        holder.itemView.setOnClickListener {
            rowIndex = holder.adapterPosition
            notifyDataSetChanged()

            when (position) {
                0 -> {

                    val antacids: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Aluminum Hydroxide + Magnesium Hydroxide + Simethicone"),
                        CategoryNames("Aluminum Hydroxide + Magnesium Trisilicate"),


                        )
                    val antiUlcer: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Cimetidine"),
                        CategoryNames("Omeprazole"),
                        CategoryNames("Ranitidine")

                    )
                    val upperGIBleeding: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Octreotide"),
                        CategoryNames("Pantoprazole"),
                        CategoryNames("Omeprazole"),
                        CategoryNames("Propranolol")

                    )
                    val antiSpasmodics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Hyoscine (Scopolamine) Butylbromide"),
                        CategoryNames("Hyoscine (Scopolamine) Hydrobromide"),

                        )
                    val antiemetics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Chlorpromazine Hydrochloride"),
                        CategoryNames("Dimenhydrinate"),
                        CategoryNames("Dexamethasone"),
                        CategoryNames("Meclizine Hydrochloride + Vitamin B6"),
                        CategoryNames("Metoclopramide Hydrochloride"),
                        CategoryNames("Ondansetron")
                    )
                    val catharticsAndLaxaties: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Bisacodyl"),
                        CategoryNames("Castor oil"),
                        CategoryNames("Glycerin"),
                        CategoryNames("Lactulose"),
                        CategoryNames("Polyethylene Glycol (PEG)"),

                        )
                    val diarrheaManagment: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Loperamide"),
                        CategoryNames("Oral Rehydration Salt"),
                        CategoryNames("Zinc Sulphate"),
                        CategoryNames("ReSoMal"),
                        CategoryNames("Polyethylene Glycol (PEG)"),

                        )
                    val antiflatulents: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Simethicone")
                    )
                    val antihaemorrhoidal: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Betamethasone Valerate + Phenylephrine HCl + LidocaineHCl"),
                        CategoryNames("Hydrocortisone + Framycetin + Cinchocaine + Esculoside"),
                    )
                    val iBS: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Sulfasalazine"),
                        CategoryNames("Azathioprine"),
                        CategoryNames("Methylprednisolone"),

                        )

                    val subgastroIntestinalMedicines: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Antacids", antacids),
                        SubCategoryData("Antiulcer agents", antiUlcer),
                        SubCategoryData("Upper GI bleeding Medicines", upperGIBleeding),
                        SubCategoryData("Antispasmodics/ Spasmolytics analgesics", antiSpasmodics),
                        SubCategoryData("Antiemetics", antiemetics),
                        SubCategoryData("Laxatives and Cathartics  ", catharticsAndLaxaties),
                        SubCategoryData("Diarrhea management Medicines", diarrheaManagment),
                        SubCategoryData("Antiflatulents", antiflatulents),
                        SubCategoryData("Antihaemorrhoidal Agents", antihaemorrhoidal),
                        SubCategoryData("Inflammatory Bowel Disease Medicines", iBS)
                    )

                    updateRecyclerView.callback(position, subgastroIntestinalMedicines)
                }
                1 -> {

                    val heartFailure: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Enalapril"),
                        CategoryNames("Captopril"),
                        CategoryNames("Lisinopril"),
                        CategoryNames("Digoxin"),
                        CategoryNames("Candesartan"),
                        CategoryNames("Valsartan"),
                        CategoryNames("losartan"),
                        CategoryNames("Spironolactone"),
                        CategoryNames("Furosemide"),
                        CategoryNames("Hydrochlorothiazide"),
                        CategoryNames("Norepinephrine"),
                        CategoryNames("Nitroglycerine"),
                        CategoryNames("Metoprolol succinate"),
                    )
                    val antiarrhythmics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Adenosine"),
                        CategoryNames("Amiodarone"),
                        CategoryNames("Atropine"),
                        CategoryNames("Digoxin"),
                        CategoryNames("Lidocaine Hydrochloride"),
                        CategoryNames("Metoprolol"),
                        CategoryNames("Verapamil"),
                        CategoryNames("Isopernaline"),
                    )
                    val antihypertensive: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Amlodipine"),
                        CategoryNames("Nifedipine"),
                        CategoryNames("Candesartan"),
                        CategoryNames("Lisinopril"),
                        CategoryNames("Captopril"),
                        CategoryNames("Enalapril"),
                        CategoryNames("Hydralazine"),
                        CategoryNames("Labetalol"),
                        CategoryNames("Carvedilol"),
                        CategoryNames("Metoprolol succinate"),
                        CategoryNames("Methyldopa"),
                    )
                    val diuretics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Furosemide"),
                        CategoryNames("Hydrochlorthiazide"),
                        CategoryNames("Indapamide"),
                        CategoryNames("Mannitol"),
                        CategoryNames("Spironolactone"),

                        )
                    val angina: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Metoprolol"),
                        CategoryNames("Atenolol"),
                        CategoryNames("Carvedilol"),
                        CategoryNames("Glyceryl Trinitrate (Nitroglycerine)"),
                        CategoryNames("Isosorbidedinitrate"),
                        CategoryNames("Verapamil")
                    )
                    val vascularShock: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Adrenaline (Epinephrine)"),
                        CategoryNames("Dopamine Hydrochloride"),
                        CategoryNames("Dobutamine"),
                        CategoryNames("Noradrenaline"),

                        )
                    val pulmonaryHypertension: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Sildenafil Citrate"),
                        CategoryNames("Bosentan"),
                    )
                    val antiLipedemics: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Atrovastatin"),
                        CategoryNames("Simvastatin"),
                        CategoryNames("Rosuvastatin"),
                        CategoryNames("Mega-3-fatty acid"),

                        )
                    val antiPlatelate: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Acetylsalicylic acid"),
                        CategoryNames("Clopidogrel"),
                    )
                    val thrombolytics: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Alteplase"),
                        CategoryNames("Streptokinase"),
                    )

                    val subCardioVascularMedicines: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Heart Failure Medicines", heartFailure),
                        SubCategoryData("Antiarrhythmics", antiarrhythmics),
                        SubCategoryData("Antihypertensive", antihypertensive),
                        SubCategoryData("Diuretics", diuretics),
                        SubCategoryData("Angina/Ischemic Heart Disease Medicines", angina),
                        SubCategoryData("Vascular shock Medicine", vascularShock),
                        SubCategoryData("Pulmonary Hypertension Medicines", pulmonaryHypertension),
                        SubCategoryData("Antilipedemic Agents", antiLipedemics),
                        SubCategoryData("Antiplatelate", antiPlatelate),
                        SubCategoryData("Thrombolytic Agents", thrombolytics)
                    )

                    updateRecyclerView.callback(position, subCardioVascularMedicines)
                }
                2 -> {
                    val antitussives: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Dextromethorphan Hydrobromide"),
                        CategoryNames("Paracetamol + Chlorpheniramine + Pseudoephedrine"),
                        CategoryNames("Guaifenesin"),
                    )
                    val antiAsthmasCOPD: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Aminophylline"),
                        CategoryNames("Beclomethasone Dipropionate"),
                        CategoryNames("Budesonide"),
                        CategoryNames("Budesonide + formoterol"),
                        CategoryNames("Fluticasone + Salmeterol"),
                        CategoryNames("Ipratropium bromide"),
                        CategoryNames("Tiotropium"),
                        CategoryNames("Salbutamol"),
                        CategoryNames("Prednisolone"),
                        CategoryNames("Hydrocortisone Succinate"),
                    )

                    val allergicRhinitis: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Mometasonefuroate"),
                        CategoryNames("Beclomethasone Dipropionate"),
                        CategoryNames("Loratidine"),
                        CategoryNames("Cetrizine"),
                        CategoryNames("Xylometazoline"),
                    )

                    val sarcoidosis: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Prednisolone"),
                        CategoryNames("Methotrexate"),
                        CategoryNames("Azathioprine"),
                        CategoryNames("Mycophenolate Mofetil"),
                    )
                    val apnea: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Caffeine citrate"),
                    )
                    val subrespiratoryMedicines: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Antitussive/Expectorant", antitussives),
                        SubCategoryData("Anti-asthmatic and COPD Medicines", antiAsthmasCOPD),
                        SubCategoryData("Allergic Rhinitis Medicines", allergicRhinitis),
                        SubCategoryData("Sarcoidosis (ILD) Medicines", sarcoidosis),
                        SubCategoryData("Apnea of prematurity Medicines", apnea),

                        )

                    updateRecyclerView.callback(position, subrespiratoryMedicines)
                }
                3 -> {

                    val Penicillines: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Amoxicillin"),
                        CategoryNames("Amoxicillin + Clavulanic Acid"),
                        CategoryNames("Ampicillin"),
                        CategoryNames("Ampicillin + Sulbactam"),
                        CategoryNames("Cloxacillin"),
                        CategoryNames("Penicillin G, Benzathin"),
                        CategoryNames("Penicillin G, Sodium Crystalline"),
                        CategoryNames("Piperacillin + tazobactam"),

                        )
                    val Macrolides: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Azithromycin"),
                        CategoryNames("Clarithromycin"),
                        CategoryNames("Erythromycin")

                    )
                    val Cephalosporins: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Cefazolin"),
                        CategoryNames("Cefotaxime Sodium"),
                        CategoryNames("Ceftazidime"),
                        CategoryNames("Ceftazidime + Avibactam"),
                        CategoryNames("Cefuroxime"),
                        CategoryNames("Cefixime"),
                        CategoryNames("Cefepime"),
                        CategoryNames("Cefpodoxime"),
                        CategoryNames("Ceftriaxone"),
                        CategoryNames("Ceftriaxone + sulbactam"),
                        CategoryNames("Cephalexin"),

                        )
                    val Carbapenems: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Meropenem"),
                        CategoryNames("Meropenem + Vaborbactam")
                    )
                    val Aminoglycosides: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Gentamicin"),
                        CategoryNames("Neomycin"),

                        )
                    val Fluroquinolones: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Ciprofloxacin"),
                        CategoryNames("Norfloxacin"),

                        )
                    val Tetracycline: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Doxycycline"),
                    )
                    val Miscellaneous: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Colistin"),
                        CategoryNames("Clindamycin"),
                        CategoryNames("Metronidazole"),
                        CategoryNames("Polymyxin B "),
                        CategoryNames("Nitrofurantoin"),
                        CategoryNames("Sulphamethoxazole + Trimethoprim"),
                        CategoryNames("Vancomycin"),
                    )

                    val antiTuberculosisDrugs: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Ethambutol"),
                        CategoryNames("Ethambutol + Isoniazid + Pyrazinamide + Rifampicin"),
                        CategoryNames("Ethambutol + Isoniazid + Rifampicin"),
                        CategoryNames("Isoniazid"),
                        CategoryNames("Isoniazid + Pyrazinamide + Rifampicin"),
                        CategoryNames("Isoniazid + Rifampicin"),
                        CategoryNames("Pyrazinamide"),
                        CategoryNames("Rifabutin"),
                        CategoryNames("Rifampicin"),
                    )

                    val antiMDRTB: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Amikacin"),
                        CategoryNames("Bedaquiline"),
                        CategoryNames("Capreomycin"),
                        CategoryNames("Clofazimine"),
                        CategoryNames("Cycloserine"),
                        CategoryNames("Ethionamide"),
                        CategoryNames("Prothionamide"),
                        CategoryNames("Kanamycin"),
                        CategoryNames("Levofloxacin"),
                        CategoryNames("Linezolid"),
                        CategoryNames("Meropenem"),
                        CategoryNames("Imipenem-cilastatin"),
                        CategoryNames("Moxifloxacin"),
                        CategoryNames("P-Aminosalycylic Acid"),
                        CategoryNames("Streptomycine"),
                    )
                    val antileproticMedicines: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Clofazimine"),
                        CategoryNames("Dapsone"),
                        CategoryNames("Rifampicin"),

                        )

                    val antibacterials: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Penicillines", Penicillines),
                        SubCategoryData("Macrolides", Macrolides),
                        SubCategoryData("Cephalosporins", Cephalosporins),
                        SubCategoryData("Carbapenems", Carbapenems),
                        SubCategoryData("Aminoglycosides", Aminoglycosides),
                        SubCategoryData("Fluroquinolones", Fluroquinolones),
                        SubCategoryData("Tetracycline", Tetracycline),
                        SubCategoryData("Miscellaneous", Miscellaneous),
                        SubCategoryData("Anti-tuberculosis drugs", antiTuberculosisDrugs),
                        SubCategoryData("Anti-MDR-TB", antiMDRTB),
                        SubCategoryData("Antileprotic medicines", antileproticMedicines),

                        )

                    updateRecyclerView.callback(position, antibacterials)
                }
                4 -> {

                    val Antifungals: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Amphotericin B"),
                        CategoryNames("Clotrimazole"),
                        CategoryNames("Flucytosine"),
                        CategoryNames("Fluconazole"),
                        CategoryNames("Griseofulvin"),
                        CategoryNames("Itraconazole"),
                        CategoryNames("Miconazole"),
                        CategoryNames("Nystatin"),
                        CategoryNames("Terbinafine Hydrochloride"),
                        CategoryNames("Voriconazole"),
                        CategoryNames("Caspofungin"),


                        )

                    val antifungals: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData(" ", Antifungals),


                        )

                    updateRecyclerView.callback(position, antifungals)
                }
                5 -> {

                    val antiretrovirals: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Abacavir Sulphate"),
                        CategoryNames("Abacavir Sulphate /Lamuvidine"),
                        CategoryNames("Atazanavir/ Ritonavir"),
                        CategoryNames("Darunavir"),
                        CategoryNames("Doltegravir"),
                        CategoryNames("Dolutegravir + lamivudine + tenofovir"),
                        CategoryNames("Efavirenz"),
                        CategoryNames("Efavirenz + Lamuvidine + Tenofovir"),
                        CategoryNames("Efavirenz + Emtricitabine + Tenofovir"),
                        CategoryNames("Emtricitabine + Tenofovir"),
                        CategoryNames("Lamivudine"),
                        CategoryNames("Lamivudine+ Zidovudine"),
                        CategoryNames("Lopinavir + Ritonavir"),
                        CategoryNames("Nevirapine"),
                        CategoryNames("Raltegravir"),
                        CategoryNames("Zidovudine"),
                    )
                    val antiHepatitisMedicines: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Tenofovirdisoproxil Fumarate"),
                        CategoryNames("Entecavir"),
                        CategoryNames("Glecaprevir + Pibrentasvir"),
                        CategoryNames("Sofosbuvir* +daclatasvir"),
                        CategoryNames("Sofosbuvir + Velpatasvir"),
                        CategoryNames("Sofosbuvir + Ledipasvir"),
                    )
                    val Miscellaneous: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Acyclovir"),
                        CategoryNames("Ganciclovir"),

                        )


                    val Antivirals: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Antiretrovirals", antiretrovirals),
                        SubCategoryData("Antihepatitis Medicines", antiHepatitisMedicines),
                        SubCategoryData("Miscellaneous", Miscellaneous),


                        )

                    updateRecyclerView.callback(position, Antivirals)
                }
                6 -> {
                    val antiMalarialMedicines: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Artemether"),
                        CategoryNames("Artemether + Lumefantrine"),
                        CategoryNames("Artesunate"),
                        CategoryNames("Chloroquine"),
                        CategoryNames("Mefloquine Hydrochloride"),
                        CategoryNames("Primaquine Phosphate"),
                        CategoryNames("Quinine- Dihydrochloride"),
                        CategoryNames("Doxycycline"),

                        )
                    val amoebiasisMedicines: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Diloxanide Furoate"),
                        CategoryNames("Metronidazole"),
                        CategoryNames("Tinidazole")

                    )
                    val giardiasisMedicines: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Metronidazole"),
                        CategoryNames("Tinidazole"),
                    )
                    val antileshimanisiasis: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Amphotericin B"),
                        CategoryNames("Miltefosine"),
                        CategoryNames("Paromomycin"),
                        CategoryNames("Sodium Stibugluconate"),
                    )
                    val Trypanocides: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Pentamidine"),
                        CategoryNames("Suramin Sodium"),
                    )
                    val toxoplasmosisMedicines: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Clindamycin"),
                        CategoryNames("Pyrimethamine"),
                        CategoryNames("Sulfamethoxazole + Trimethoprim"),
                        CategoryNames("Sulfadiazine + Folinic Acid"),
                    )


                    val Antiprotozoals: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData(" Anti-malarial medicines", antiMalarialMedicines),
                        SubCategoryData("Amoebiasis Medicines", amoebiasisMedicines),
                        SubCategoryData("Giardiasis Medicines", giardiasisMedicines),
                        SubCategoryData("Antileshimanisiasis", antileshimanisiasis),
                        SubCategoryData("Trypanocides", Trypanocides),
                        SubCategoryData("Toxoplasmosis Medicines", toxoplasmosisMedicines),

                        )

                    updateRecyclerView.callback(position, Antiprotozoals)
                }
                7 -> {
                    val Filaricides: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Albendazol"),
                        CategoryNames("Diethyl Carbamazine Citrate"),
                        CategoryNames("Ivermectin"),


                        )
                    val Schistosomicides: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Praziquantel"),

                        )
                    val Miscellaneous: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Albendazol"),
                        CategoryNames("Piperazine"),
                        CategoryNames("Mebendazole"),

                        )


                    val Antihelmentics: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Filaricides", Filaricides),
                        SubCategoryData("Schistosomicides", Schistosomicides),
                        SubCategoryData("Miscellaneous", Miscellaneous),
                    )

                    updateRecyclerView.callback(position, Antihelmentics)
                }
                8 -> {

                    val antipainAndPalliative: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Acetaminophen"),
                        CategoryNames("Acetylsalicylic Acid"),
                        CategoryNames("Diclofenac"),
                        CategoryNames("Ibuprofen"),
                        CategoryNames("Morphine"),
                        CategoryNames("Fentanyl"),
                        CategoryNames("Pethidine"),
                        CategoryNames("Amitriptyline"),
                        CategoryNames("Gabapentin"),
                        CategoryNames("Carbamazepine"),
                        CategoryNames("Tramadol hydrochloride"),

                        )
                    val Antimigrainemedicine: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Acetylsalicylic Acid"),
                        CategoryNames("Ibuprofen"),
                        CategoryNames("Paracetamol"),
                        CategoryNames("Propranolol"),
                        CategoryNames("Sumatriptan"),

                        )
                    val HypnoticsAndAnxiolytics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Diazepam"),
                        CategoryNames("Midazolam hydrochloride"),
                        CategoryNames("Bromazepam"),
                        CategoryNames("Lorazepam"),
                    )
                    val Antidepressant: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Amitriptyline"),
                        CategoryNames("Fluoxetine"),
                        CategoryNames("Impramine"),
                        CategoryNames("Sertaline Hydrochloride"),
                    )
                    val Anticonvulsants: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Carbamazepine"),
                        CategoryNames("Clonazepam"),
                        CategoryNames("Diazepam"),
                        CategoryNames("Lamotrigine"),
                        CategoryNames("Lorazepam"),
                        CategoryNames("Magnesium sulphate"),
                        CategoryNames("Phenytoin (Sodium salt)"),
                        CategoryNames("Phenobarbitone(Phenobarbital)"),
                        CategoryNames("Sodium Valproate"),
                        CategoryNames("Gabapentin"),
                        CategoryNames("Ethiosuxumide"),

                        )
                    val Antipsychoticmedicines: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Chlorpromazine Hydrochloride"),
                        CategoryNames("Clozapine"),
                        CategoryNames("Fluphenazine Decanoate"),
                        CategoryNames("Haloperidol"),
                        CategoryNames("Olanzapine"),
                        CategoryNames("Quetiapine"),
                        CategoryNames("Risperidone"),
                    )
                    val Moodstabilizers: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Carbamazepine"),
                        CategoryNames("Lithium Carbonate"),
                        CategoryNames("Sodium Valproate"),
                    )
                    val SubstanceAbuseandDependence: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Bupropion"),
                        CategoryNames("Methadone Hydrochloride"),
                        CategoryNames("Naltrexone Hydrochloride"),
                        CategoryNames("Nicotine Replacement Therapy"),
                    )

                    val AttentionDeficitDisorder: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Bupropion"),
                        CategoryNames("Methylphenidate"),
                        CategoryNames("Dextroamphetamine"),

                        )

                    val Antiparkinsonmedicines: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Benzhexol/Trihexphenidyl"),
                        CategoryNames("Levodopa + Carbidopa"),
                        CategoryNames("Benzotropine"),
                        CategoryNames("Valbenazine"),
                    )


                    val CNS: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData(
                            "Pain Management and Palliative care",
                            antipainAndPalliative
                        ),
                        SubCategoryData("Antimigraine medicine", Antimigrainemedicine),
                        SubCategoryData("Hypnotics and Anxiolytics", HypnoticsAndAnxiolytics),
                        SubCategoryData("Antidepressnats", Antidepressant),
                        SubCategoryData("Anticonvulsants", Anticonvulsants),
                        SubCategoryData("Antipsychotics", Antipsychoticmedicines),
                        SubCategoryData("Mood stabilizers", Moodstabilizers),
                        SubCategoryData(
                            "Substance Abuse and Dependence medicines",
                            SubstanceAbuseandDependence
                        ),
                        SubCategoryData(
                            "Attention Deficit Disorder medicines",
                            AttentionDeficitDisorder
                        ),
                        SubCategoryData("Antiparkinson medicines", Antiparkinsonmedicines),

                        )

                    updateRecyclerView.callback(position, CNS)
                }
                9 -> {
                    val GeneralAnesthetics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Etomidate"),
                        CategoryNames("Halothane"),
                        CategoryNames("Isoflurane"),
                        CategoryNames("Sevoflurane"),
                        CategoryNames("Ketamine"),
                        CategoryNames("Nitrous Oxide"),
                        CategoryNames("Propofol"),
                        CategoryNames("Dexmeditomedine"),
                        CategoryNames("Fentanyl"),
                        CategoryNames("Remifentanyl"),
                        CategoryNames("Thiopental Sodium"),

                        )
                    val LocalAnesthetics: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Bupivacaine"),
                        CategoryNames("Lidocaine Hydrochloride"),
                        CategoryNames("Lidocaine Hydrochloride + Adrenaline"),
                    )
                    val AnestheticsAdjunctsAndAdjuvants: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Atropine sulphate"),
                        CategoryNames("Ephedrine Hydrochloride"),
                        CategoryNames("Glycopyrronium"),
                        CategoryNames("Neostigmine"),
                        CategoryNames("Paracetamol"),
                        CategoryNames("Phenylephrine Hydrochloride"),
                    )

                    val Neuromescularblockers: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Pancuronium Bromide"),
                        CategoryNames("Suxamethonium Chloride"),
                        CategoryNames("Rocuronium"),
                        CategoryNames("Cisatracurium"),
                        CategoryNames("Atracurium"),
                        CategoryNames("Vecuronium Bromide"),
                    )


                    val ANESTHESIAMEDICINES: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("General Anesthetics", GeneralAnesthetics),
                        SubCategoryData("Local Anesthetics", LocalAnesthetics),
                        SubCategoryData(
                            "Anesthetics Adjuncts and Adjuvants",
                            AnestheticsAdjunctsAndAdjuvants
                        ),
                        SubCategoryData("Neuromescular blockers", Neuromescularblockers),
                    )

                    updateRecyclerView.callback(position, ANESTHESIAMEDICINES)
                }
                10 -> {
                    val Antirheumatics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Acetylsalicylic Acid"),
                        CategoryNames("Diclofenac"),
                        CategoryNames("Ibuprofen"),
                        CategoryNames("Indomethacin"),


                        )
                    val Gout: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Allopurinol"),
                        CategoryNames("Ibuprofen"),
                        CategoryNames("Indomethacin"),
                        CategoryNames("Colchicine"),
                    )
                    val DiseaseModifyingAntirheumaticMedicin: MutableList<CategoryNames> =
                        mutableListOf(
                            CategoryNames("Methotrexate"),
                            CategoryNames("Sulfasalazine"),
                            CategoryNames("Chloroquine phosphate"),
                        )
                    val MusculoskeletalandJoint: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Antirheumatics", Antirheumatics),
                        SubCategoryData("Medicines used for Gout", Gout),
                        SubCategoryData(
                            "Disease Modifying Anti-rheumatic Medicine",
                            DiseaseModifyingAntirheumaticMedicin
                        ),

                        )

                    updateRecyclerView.callback(position, MusculoskeletalandJoint)
                }
                11 -> {
                    val Vitamin: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Ascorbic acid (Vitamin C)"),
                        CategoryNames("Cholecalciferol (Vitamin D3)"),
                        CategoryNames("Ergocalciferol (Vitamin D2)"),
                        CategoryNames("Cyanocobalamine (Vitamin B12)"),
                        CategoryNames("Folic Acid"),
                        CategoryNames("Phytomenadione (Vitamin K1)"),
                        CategoryNames("Pyridoxine Hydrochloride (Vitamin B6)"),
                        CategoryNames("Thiamine Hydrochloride(Vitamin B1)"),
                        CategoryNames("Vitamin A"),
                    )
                    val VitaminBcomplexPreparation: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Vitamin B1 + B6 + B12"),
                    )
                    val Multivitamins: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Multivitamin with Minerals and/or Extracts"),
                    )
                    val Vitamins: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Vitamins", Vitamin),
                        SubCategoryData("Vitamin B-complex Preparation", VitaminBcomplexPreparation),
                        SubCategoryData("Multivitamins", Multivitamins),
                        )
                    updateRecyclerView.callback(position, Vitamins)
                }
                12 -> {
                    val MedicinesUsedInAllergy: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Cetrizine"),
                        CategoryNames("Chlorpheniramine malate"),
                        CategoryNames("Diphenhydramine hydrochloride"),
                        CategoryNames("Loratidine"),
                        CategoryNames("Desloratidine"),
                        CategoryNames("Promethazine Hydrochloride"),
                        CategoryNames("Pyridoxine Hydrochloride (Vitamin B6)"),
                        CategoryNames("Thiamine Hydrochloride(Vitamin B1)"),
                        CategoryNames("Vitamin A"),
                    )
                    val MedicinesUsedInAllergicEmergencies: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Adrenaline (Epinephrine)"),
                        CategoryNames("Dexamethasone"),
                        CategoryNames("Hydrocortisone (Sodium Succinate)"),
                        CategoryNames("Loratadine"),
                        CategoryNames("Prednisolone"),
                    )
                    val ANTHISTAMINES: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Medicines used in Allergy", MedicinesUsedInAllergy),
                        SubCategoryData("Medicines used in Allergic Emergencies", MedicinesUsedInAllergicEmergencies),

                    )
                    updateRecyclerView.callback(position, ANTHISTAMINES)
                }
                13 -> {
                    val CorticosteriodsPreparation: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Betamethasone"),
                        CategoryNames("Dexamethasone"),
                        CategoryNames("Hydrocortisone"),
                        CategoryNames("Methylprednisolone Acetate"),
                        CategoryNames("Prednisolone"),
                        CategoryNames("Triamcinolone Acetonide"),
                        CategoryNames("Fludrocortisone"),

                    )
                    val ThyroidHormoneAgents: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Aqueous Iodine Solution (Iodine + Potassium Iodide)"),
                        CategoryNames("Carbimazole"),
                        CategoryNames("PropylThiouracil(PTU)"),
                        CategoryNames("Levothyroxine"),
                        CategoryNames("Propranolol"),
                    )
                    val DiabetesMedicines: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Glibenclamide"),
                        CategoryNames("Gliclazide "),
                        CategoryNames("Glimperide"),
                        CategoryNames("Glucagon"),
                        CategoryNames("Insulin"),
                        CategoryNames("Metformin"),
                        CategoryNames("Dapagliflozin"),
                    )
                    val FemaleSexHormonePreparations: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Norethindrone (Norethisterone)"),
                        CategoryNames("Hydroxyprogesterone Caproate"),
                        CategoryNames("Estradiol"),
                        CategoryNames("Medroxyprogesteronen acetate"),
                        )
                    val EndocrineDisorderMedicines: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Corticosteriods Preparation", CorticosteriodsPreparation),
                        SubCategoryData("Thyroid Hormone and Anti-thyroid Agents", ThyroidHormoneAgents),
                        SubCategoryData("Drugs used for management of Blood Suger", DiabetesMedicines),
                        SubCategoryData("Female sex hormone preparations", FemaleSexHormonePreparations),
                    )
                    updateRecyclerView.callback(position, EndocrineDisorderMedicines)
                }
                14 -> {
                    val  CombinedOralContraceptives: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Levonorgestrel (D-Norgestrel) + Ethinylestradiol with/ without Iron"),

                    )
                    val EmergencyContraceptive: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Leovonorgestrel (D-Nongestrel)"),
                    )
                    val ProgesteroneOnlyContraceptives: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Medroxyprogesterone Acetate"),
                        CategoryNames("Norethindrone"),
                    )
                    val ContraceptiveDevicesAndBarriers: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Condoms"),
                        CategoryNames("Levonorgestrel-releasing intrauterine system"),
                        CategoryNames("Copper T380A"),
                    )
                    val ImplantableContraceptives: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Etonogestrel-releasing implant"),
                        CategoryNames("levonorgestrel-releasing implant"),

                    )
                    val Contraceptives: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Combined oral Contraceptives", CombinedOralContraceptives),
                        SubCategoryData("Emergency Contraceptive", EmergencyContraceptive),
                        SubCategoryData("Progesterone only contraceptives", ProgesteroneOnlyContraceptives),
                        SubCategoryData("Contraceptive Devices and Barriers", ContraceptiveDevicesAndBarriers),
                        SubCategoryData("Implantable contraceptives", ImplantableContraceptives),
                    )
                    updateRecyclerView.callback(position, Contraceptives)
                }
                15 -> {
                    val  Medications: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Clomifene citrate"),
                        CategoryNames("Ergometrin"),
                        CategoryNames("Magnesium sulphate"),
                        CategoryNames("Misoprostol"),
                        CategoryNames("Misoprostol + Mifeprostone"),
                        CategoryNames("Pethidine Hydrochloride"),
                        CategoryNames("Dinoprostone (Prostaglandine E2 )"),
                        CategoryNames("Oxytocin"),
                        CategoryNames("Nifedipine"),
                        CategoryNames("Salbutamol"),
                        CategoryNames("Tranexamic acid"),
                        CategoryNames("Caffeine citrate"),
                        CategoryNames("Dexamethasone"),
                        CategoryNames("Bethamethasone"),
                        CategoryNames("Norethistirone"),
                        )
                    val OBGYN: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Medications", Medications),
                        )
                    updateRecyclerView.callback(position, OBGYN)
                }
                16 -> {
                    val  Anticoagulants: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Heparin (unfractionated heparine)"),
                        CategoryNames("Warfarin Sodium"),
                        CategoryNames("Enoxaparin"),
                        CategoryNames("Rivaroxaban"),
                        CategoryNames("Apixaban"),
                        CategoryNames("Edoxaban"),
                        CategoryNames("Dabigatran"),
                        )
                    val HemostaticAgents: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Phytomenadione (Vitamin K1)"),
                    )
                    val AntianemicAgents: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Cyanocobalamin (Vitamin B12)"),
                        CategoryNames("Ferrous Salt"),
                        CategoryNames("Ferrous Salt + Folic Acid"),
                        CategoryNames("Folinic Acid"),
                        CategoryNames("Iron Complex"),
                        CategoryNames("Folic Acid"),
                        CategoryNames("Erythropoiesis-stimulating agents"),
                    )
                    val AntihemophilicAgent: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Desmopressin"),
                        CategoryNames("Coagulation factor VIII"),
                        CategoryNames("Coagulation factor IX"),
                    )
                    val Antiplatelate: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Acetylsalicylic Acid"),
                        CategoryNames("Clopidogrel"),

                        )
                    val BloodSubstituteandPlasmaExpander: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Dextran (Mw 40,000)"),
                        CategoryNames("Dextran (MW 70,000)"),
                        CategoryNames("Albumin"),


                        )

                    val BloodProductsAndMedicines: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Anticoagulants", Anticoagulants),
                        SubCategoryData("Hemostatic Agents", HemostaticAgents),
                        SubCategoryData("Antianemic Agents", AntianemicAgents),
                        SubCategoryData("Antihemophilic agent", AntihemophilicAgent),
                        SubCategoryData("Antiplatelate", Antiplatelate),
                        SubCategoryData("Blood substitute and plasma expander", BloodSubstituteandPlasmaExpander),
                    )
                    updateRecyclerView.callback(position, BloodProductsAndMedicines)
                }
                else -> {

                    val antacids: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Aluminum Hydroxide + Magnesium Hydroxide + Simethicone"),
                        CategoryNames("Aluminum Hydroxide + Magnesium Trisilicate"),


                        )
                    val antiUlcer: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Cimetidine"),
                        CategoryNames("Omeprazole"),
                        CategoryNames("Ranitidine")

                    )
                    val upperGIBleeding: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Octreotide"),
                        CategoryNames("Pantoprazole"),
                        CategoryNames("Omeprazole"),
                        CategoryNames("Propranolol")

                    )
                    val antiSpasmodics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Hyoscine (Scopolamine) Butylbromide"),
                        CategoryNames("Hyoscine (Scopolamine) Hydrobromide"),

                        )
                    val antiemetics: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Chlorpromazine Hydrochloride"),
                        CategoryNames("Dimenhydrinate"),
                        CategoryNames("Dexamethasone"),
                        CategoryNames("Meclizine Hydrochloride + Vitamin B6"),
                        CategoryNames("Metoclopramide Hydrochloride"),
                        CategoryNames("Ondansetron")
                    )
                    val catharticsAndLaxaties: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Bisacodyl"),
                        CategoryNames("Castor oil"),
                        CategoryNames("Glycerin"),
                        CategoryNames("Lactulose"),
                        CategoryNames("Polyethylene Glycol (PEG)"),

                        )
                    val diarrheaManagment: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Loperamide"),
                        CategoryNames("Oral Rehydration Salt"),
                        CategoryNames("Zinc Sulphate"),
                        CategoryNames("ReSoMal"),
                        CategoryNames("Polyethylene Glycol (PEG)"),

                        )
                    val antiflatulents: MutableList<CategoryNames> = mutableListOf(
                        CategoryNames("Simethicone")
                    )
                    val antihaemorrhoidal: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Betamethasone Valerate + Phenylephrine HCl + LidocaineHCl"),
                        CategoryNames("Hydrocortisone + Framycetin + Cinchocaine + Esculoside"),
                    )
                    val iBS: MutableList<CategoryNames> = mutableListOf(

                        CategoryNames("Sulfasalazine"),
                        CategoryNames("Azathioprine"),
                        CategoryNames("Methylprednisolone"),

                        )

                    val subgastroIntestinalMedicines: MutableList<SubCategoryData> = mutableListOf(
                        SubCategoryData("Antacids", antacids),
                        SubCategoryData("Antiulcer agents", antiUlcer),
                        SubCategoryData("Upper GI bleeding Medicines", upperGIBleeding),
                        SubCategoryData("Antispasmodics/ Spasmolytics analgesics", antiSpasmodics),
                        SubCategoryData("Antiemetics", antiemetics),
                        SubCategoryData("Laxatives and Cathartics  ", catharticsAndLaxaties),
                        SubCategoryData("Diarrhea management Medicines", diarrheaManagment),
                        SubCategoryData("Antiflatulents", antiflatulents),
                        SubCategoryData("Antihaemorrhoidal Agents", antihaemorrhoidal),
                        SubCategoryData("Inflammatory Bowel Disease Medicines", iBS)
                    )

                    updateRecyclerView.callback(position, subgastroIntestinalMedicines)
                }
            }

        }

        if (select) {
            if (position == 0)
                holder.itemView.category_title_linear_layout.setBackgroundResource(R.drawable.category_names_recycler_selected_background)
            select = false
        } else {
            if (rowIndex == holder.adapterPosition) {
                holder.itemView.category_title_linear_layout.setBackgroundResource(R.drawable.category_names_recycler_selected_background)
            } else {
                holder.itemView.category_title_linear_layout.setBackgroundResource(R.drawable.category_names_recycler_background)
            }
        }


    }

    override fun getItemCount(): Int = mainCategoryNamesList.size

    class CategoryNamesStaticViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View
        private var name: TextView
        private lateinit var categoryNamesList: CategoryNames


        init {
            view = v
            name = v.findViewById(R.id.category_title_name)

        }


        fun bindCategoryNamesList(categoryNamesList: CategoryNames) {
            this.categoryNamesList = categoryNamesList
            name.text = categoryNamesList.name


        }


    }


}
