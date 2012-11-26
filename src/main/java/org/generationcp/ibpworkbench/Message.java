package org.generationcp.ibpworkbench;

public enum Message {
     ACCOUNT
    ,ACTIONS
    ,ACTIVITY_NEXT
    ,ACTIVITY_RECENT
    ,CONTACT_CREATE
    ,BREEDING_VIEW_DATASET_SELECT
    ,DASHBOARD
    ,DATASETS
    ,ERROR_DATABASE
    ,ERROR_IN_GETTING_TOP_LEVEL_STUDIES
    ,ERROR_IN_GETTING_STUDIES_BY_PARENT_FOLDER_ID
    ,ERROR_LOGIN_INVALID
    ,TOOL_VERSIONS
    ,HELP
    ,HOME
    ,EMAIL
    ,LOGIN
    ,PASSWORD
    ,LOGIN_TITLE
    ,PROJECT_CREATE
    ,PROJECT_DASHBOARD_TITLE
    ,PROJECT_TITLE
    ,PROJECT_TABLE_CAPTION
    ,PROJECT
    ,ACTION
    ,DATE
    ,DATE_DUE
    ,OWNER
    ,STATUS
    ,RECENT
    ,SIGNOUT
    ,USER_GUIDE
    ,USER_GUIDE_1
    ,USERNAME
    ,WORKBENCH_TITLE
    
    // Workbench Dashboard Window
    ,TOOL_NAME
    ,VERSION
    
    // Workbench Dashboard
    ,ACTIVITIES
    ,PROJECT_DETAIL
    ,ROLES
    ,ROLE_TABLE_TITLE
    ,PROJECT_TABLE_TOOLTIP
    ,ROLE_TABLE_TOOLTIP
    ,START_DATE
    
    //General
    ,SAVE,
    CANCEL,
    
    //Register User Account
    REGISTER_USER_ACCOUNT,
    REGISTER_USER_ACCOUNT_FORM,
    REGISTER_SUCCESS,
    REGISTER_SUCCESS_DESCRIPTION,
    USER_ACC_POS_TITLE,
    USER_ACC_FNAME,
    USER_ACC_MIDNAME,
    USER_ACC_LNAME,
    USER_ACC_EMAIL,
    USER_ACC_USERNAME,
    USER_ACC_PASSWORD,
    USER_ACC_PASSWORD_CONFIRM,
    
    //Error Notification
    UPLOAD_ERROR,
    UPLOAD_ERROR_DESC,
    LAUNCH_TOOL_ERROR,
    LAUNCH_TOOL_ERROR_DESC,
    INVALID_TOOL_ERROR_DESC,
    LOGIN_ERROR,
    LOGIN_DB_ERROR_DESC,
    DATABASE_ERROR,
    SAVE_PROJECT_ERROR_DESC,
    SAVE_USER_ACCOUT_ERROR_DESC,
    ADD_CROP_TYPE_ERROR_DESC,
    FILE_NOT_FOUND_ERROR,
    FILE_NOT_FOUND_ERROR_DESC,
    FILE_ERROR,
    FILE_CANNOT_PROCESS_DESC,
    FILE_CANNOT_OPEN_DESC,
    PARSE_ERROR,
    WORKFLOW_DATE_PARSE_ERROR_DESC,
    CONFIG_ERROR,
    CONTACT_ADMIN_ERROR_DESC,
    INVALID_URI_ERROR,
    INVALID_URI_ERROR_DESC,
    INVALID_URI_SYNTAX_ERROR_DESC,
    INVALID_URL_PARAM_ERROR,
    INVALID_URL_PARAM_ERROR_DESC,
    
    //Tray Notification
    UPLOAD_SUCCESS,
    UPLOAD_SUCCESS_DESC,
    
    LOC_NAME
    ,LOC_ABBR
    ,BREED_METH_NAME
    ,BREED_METH_DESC
    ,BREED_METH_GRP
    ,BREED_METH_CODE
    ,BREED_METH_TYPE
    
    // Tool configuration update
    ,UPDATING
    ,UPDATING_TOOLS_CONFIGURATION
    
    // Create new project
    ,BASIC_DETAILS_LABEL
    ,USER_ROLES_LABEL
    ,PROJECT_MEMBERS_LABEL
    ,BREEDING_METHODS_LABEL
    ,LOCATIONS_LABEL
    
    // MARS Workflow Diagram
    ,PROJECT_PLANNING
    ,POPULATION_DEVELOPMENT
    ,FIELD_TRIAL_MANAGEMENT
    ,GENOTYPING
    ,PHENOTYPIC_ANALYSIS
    ,QTL_ANALYSIS
    ,QTL_SELECTION
    ,RECOMBINATION_CYCLE
    ,FINAL_BREEDING_DECISION
    ,BROWSE_GERMPLASM_INFORMATION
    ,BROWSE_STUDIES_AND_DATASETS
    ,BROWSE_GERMPLAM_LISTS
    ,BREEDING_MANAGER
    ,FIELDBOOK
    ,MANAGE_GENOTYPING_DATA
    ,BREEDING_VIEW
    ,OPTIMAS
    ,CLICK_TO_LAUNCH_GERMPLASM_BROWSER
    ,CLICK_TO_LAUNCH_STUDY_BROWSER
    ,CLICK_TO_LAUNCH_GERMPLASM_LIST_BROWSER
    ,CLICK_TO_LAUNCH_BREEDING_MANAGER
    ,CLICK_TO_LAUNCH_FIELDBOOK
    ,CLICK_TO_LAUNCH_GDMS
    ,CLICK_TO_LAUNCH_BREEDING_VIEW
    ,CLICK_TO_LAUNCH_OPTIMAS
    
    //Breeding View Window
    ,NAME_HEADER
    ,DESCRIPTION_HEADER
    ,PROPERTY_HEADER
    ,SCALE_HEADER
    ,METHOD_HEADER
    ,DATATYPE_HEADER
    ,ERROR_IN_GETTING_DATASET_FACTOR
    ,ERROR_IN_GETTING_DATASET_VARIATE
    ,ERROR_PLEASE_CONTACT_ADMINISTRATOR
}
