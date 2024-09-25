package raf.dsw.gerumap.gui.swing.controller;

import org.w3c.dom.html.HTMLFieldSetElement;

public class ActionManager {

    private NewAction newAction;
    private InfoAction infoAction;
    private RemoveAction removeAction;
    private PojamAction pojamAction;
    private VezaAction vezaAction;
    private DeleteElementAction deleteElementAction;
    private MoveAction moveAction;
    private SelectAction selectAction;
    private StyleAction styleAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private RedoAction redoAction;
    private UndoAction undoAction;
    private ExportImageAction exportImageAction;
    private SaveAsAction saveAsAction;
    private OpenProjectAction openProjectAction;
    private CentralniPojamAction centralniPojamAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        newAction = new NewAction();
        infoAction = new InfoAction();
        removeAction = new RemoveAction();
        pojamAction = new PojamAction();
        vezaAction =  new VezaAction();
        deleteElementAction = new DeleteElementAction();
        moveAction = new MoveAction();
        selectAction = new SelectAction();
        styleAction = new StyleAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        redoAction = new RedoAction();
        undoAction = new UndoAction();
        exportImageAction = new ExportImageAction();
        saveAsAction = new SaveAsAction();
        openProjectAction = new OpenProjectAction();
        centralniPojamAction = new CentralniPojamAction();


    }

    public NewAction getNewAction() {
        return newAction;
    }

    public void setNewAction(NewAction newAction) {
        this.newAction = newAction;
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public RemoveAction getRemoveAction() {
        return removeAction;
    }

    public void setRemoveAction(RemoveAction removeAction) {
        this.removeAction = removeAction;
    }

    public PojamAction getPojamAction() {
        return pojamAction;
    }

    public void setPojamAction(PojamAction pojamAction) {
        this.pojamAction = pojamAction;
    }

    public VezaAction getVezaAction() {
        return vezaAction;
    }

    public void setVezaAction(VezaAction vezaAction) {
        this.vezaAction = vezaAction;
    }

    public DeleteElementAction getDeleteElementAction() {
        return deleteElementAction;
    }

    public void setDeleteElementAction(DeleteElementAction deleteElementAction) {
        this.deleteElementAction = deleteElementAction;
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(MoveAction moveAction) {
        this.moveAction = moveAction;
    }

    public SelectAction getSelectAction() {
        return selectAction;
    }

    public void setSelectAction(SelectAction selectAction) {
        this.selectAction = selectAction;
    }

    public StyleAction getStyleAction() {
        return styleAction;
    }

    public void setStyleAction(StyleAction styleAction) {
        this.styleAction = styleAction;
    }

    public ZoomInAction getZoomInAction() {
        return zoomInAction;
    }

    public void setZoomInAction(ZoomInAction zoomInAction) {
        this.zoomInAction = zoomInAction;
    }

    public ZoomOutAction getZoomOutAction() {
        return zoomOutAction;
    }

    public void setZoomOutAction(ZoomOutAction zoomOutAction) {
        this.zoomOutAction = zoomOutAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public ExportImageAction getExportImageAction() {
        return exportImageAction;
    }

    public void setExportImageAction(ExportImageAction exportImageAction) {
        this.exportImageAction = exportImageAction;
    }

    public SaveAsAction getSaveAsAction() {
        return saveAsAction;
    }

    public void setSaveAsAction(SaveAsAction saveAsAction) {
        this.saveAsAction = saveAsAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public void setOpenProjectAction(OpenProjectAction openProjectAction) {
        this.openProjectAction = openProjectAction;
    }

    public CentralniPojamAction getCentralniPojamAction() {
        return centralniPojamAction;
    }

    public void setCentralniPojamAction(CentralniPojamAction centralniPojamAction) {
        this.centralniPojamAction = centralniPojamAction;
    }
}
