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
