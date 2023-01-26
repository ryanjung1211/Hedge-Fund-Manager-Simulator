package Frontend;

public class DifficultyFacade {
    private Difficulty easy;
    private Difficulty medium;
    private Difficulty hard;

    public DifficultyFacade()
    {
        easy = new EasyDifficulty();
        medium = new MediumDifficulty();
        hard = new HardDifficulty();
    }

    public void easyInitializeAccount()
    {
        easy.initializeAccount();
    }

    public void mediumInitializeAccount()
    {
        medium.initializeAccount();
    }

    public void hardInitializeAccount()
    {
        hard.initializeAccount();
    }
}
