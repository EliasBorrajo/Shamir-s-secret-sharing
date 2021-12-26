package ch.hevs.ui.commands;

import ch.hevs.tools.generateParts.GenerateParts;
import picocli.CommandLine;

@CommandLine.Command(
        name = "GenParts",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Generate the Shamir secret parts",
        optionListHeading = "%nOptions are:%n")

public class Generate implements Runnable
{
    //*******************************************************************************
    //  A T T R I B U T S
    //*******************************************************************************

    // Generate parts
    @CommandLine.Option(names = {"-g", "--GenPart"}, required = true, description = "Generate parts")
    boolean GenPart = false;

    // #Bytes (modulo)
    @CommandLine.Parameters(paramLabel = "<bytes>",
            description = "Modulo")
    private int bytes; //@TODO, ce sont des int et non des bytes ;)

    // #Parts
    @CommandLine.Parameters(paramLabel = "<parts>", description = "Number of parts to be generated")
    private int parts;

    // Threshold (k  <= #Parts)
    @CommandLine.Parameters(paramLabel = "<threshold>", description = "threshold")
    private int threshold;

    //*******************************************************************************
    //  M E T H O D S
    //*******************************************************************************
    @Override
    public void run()
    {
        System.out.println("Generate parts run");

        GenerateParts gnP = new GenerateParts(bytes, parts, threshold);
    }
}
