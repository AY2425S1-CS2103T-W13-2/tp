package careconnect.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import careconnect.logic.Messages;
import careconnect.logic.commands.FindCommand;
import careconnect.logic.parser.exceptions.ParseException;
import careconnect.model.person.NameAndAddressContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format or if
     *     the search term is too short
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        requireNonNull(args);
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        } else if (trimmedArgs.length() < 2) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_TOO_SHORT_SEARCH, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_ADDRESS);
        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_ADDRESS);

        String[] nameKeywords = {};
        String[] addressKeywords = {};
        if (!argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()
                && !argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE)
                );
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            String nameString = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();
            nameKeywords = nameString.split("\\s+");
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            String addressString = argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get();
            addressKeywords = addressString.split("\\s+");
        }


        return new FindCommand(
                new NameAndAddressContainsKeywordPredicate(Arrays.asList(nameKeywords), Arrays.asList(addressKeywords))
                );
    }

}
