/*
package com.instruction;

import android.content.Context;

import androidx.preference.PreferenceManager;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.hasura.model.InsertStartLearningEventMutation;
import com.samagra.commons.models.FormStructure;
import com.samagra.commons.models.StartLearningEventData;
import com.samagra.grove.logging.Grove;
import com.samagra.parent.base.BasePresenter;
import com.samagra.parent.data.network.ApolloQueryResponseListener;
import com.samagra.parent.data.network.EventDataModel;
import com.samagra.parent.helper.BackendNwHelper;
import com.samagra.parent.helper.StudentFormListDownloadListener;
import com.samagra.parent.ui.student_learning.studenthome.StudentFormDownloadListener;

import org.jetbrains.annotations.Nullable;
import org.odk.collect.android.contracts.IFormManagementContract;
import org.odk.collect.android.formmanagement.ServerFormDetails;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

*/
/**
 * The Presenter class for Home Screen. This class controls interaction between the View and Data.
 * This class <b>must</b> implement the {@link SubjectInstructionsMvpPresenter} and <b>must</b> be a type of {@link BasePresenter}.
 * s
 *
 * @author Pranav Sharma
 *//*

public class SubjectInstructionsPresenter<V extends SubjectInstructionsMvpView, I extends SubjectInstructionsMvpInteractor> extends BasePresenter<V, I> implements SubjectInstructionsMvpPresenter<V, I> {

    public void checkForFormUpdates(@Nullable String subject, Context activityContext, boolean networkConnected) {
        ArrayList<FormStructure> overallFormList = getIFormManagementContract().downloadFormList(PreferenceManager.getDefaultSharedPreferences(activityContext).
                getString("assessment_form_list", ""));
        ArrayList<FormStructure> filteredFormList = new ArrayList<>();
        for (FormStructure formStructure : overallFormList) {
            if (formStructure.getSubject().equals(subject)) filteredFormList.add(formStructure);
        }
        if (getIFormManagementContract().checkIfODKFormsMatch(subject,filteredFormList)) {
            ((SubjectInstructionScreen) activityContext).renderLayoutVisible();
        } else {
            if (networkConnected) {
                getIFormManagementContract().startDownloadODKFormListTask(new StudentFormListDownloadListener(activityContext, this, getIFormManagementContract(),filteredFormList));
            } else {
                ((SubjectInstructionScreen) activityContext).showNoInternetMessage();
            }
        }
        if (!PreferenceManager.getDefaultSharedPreferences(activityContext).getBoolean("assessment_sample_updated", false)) {
            //Forms need no download again
            if (subject.equals("Hindi")) {
                if (PreferenceManager.getDefaultSharedPreferences(activityContext).getBoolean("hindi_assessment_sample_updated", false)) {
                    //Hindi forms need to be checked for
                } else {
                    //Hindi forms need no download
                }
            } else {
                if (PreferenceManager.getDefaultSharedPreferences(activityContext).getBoolean("math_assessment_sample_updated", false)) {
//Maths forms need to be checked for
                } else {
//Maths forms need no download
                }
            }
        }
    }

    public void sendData(String jwtToken, ArrayList<StartLearningEventData> startLearningEventData, Context context) {
        EventDataModel eventDataModel = new EventDataModel(jwtToken);
        eventDataModel.uploadStartLearningEvent(startLearningEventData,
                new ApolloQueryResponseListener<InsertStartLearningEventMutation.Data>() {
                    @Override
                    public void onResponseReceived(Response<InsertStartLearningEventMutation.Data> responseData) {
                        if (responseData != null && responseData.getErrors() == null && responseData.getData() != null &&
                                responseData.getData().insert_start_learning_event() != null &&
                                responseData.getData().insert_start_learning_event().affected_rows() > 0) {
                            ((SubjectInstructionScreen) context).successReceived();
                        } else {
                            ((SubjectInstructionScreen) context).failure(startLearningEventData);
                        }
                    }

                    @Override
                    public void onFailureReceived(ApolloException apolloException) {
                        ((SubjectInstructionScreen) context).failure(startLearningEventData);
                    }

                });
    }

    @Inject
    public SubjectInstructionsPresenter(I mvpInteractor, CompositeDisposable compositeDisposable, BackendNwHelper backendNwHelper, IFormManagementContract iFormManagementContract) {
        super(mvpInteractor, compositeDisposable, backendNwHelper, iFormManagementContract);
    }
    public void startFormDownloading(HashMap<String, ServerFormDetails> newAssessmentsToBeDownloaded, Context context) {
        Grove.d("<>Form Downloading Starts at  >> " + System.currentTimeMillis());
        getIFormManagementContract().downloadODKForms(new StudentFormDownloadListener(context), newAssessmentsToBeDownloaded, true);
    }

    public void renderLayoutVisible(Context context) {
        ((SubjectInstructionScreen) context).renderLayoutVisible();
    }

}*/
