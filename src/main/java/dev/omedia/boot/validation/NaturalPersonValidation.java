package dev.omedia.boot.validation;

import dev.omedia.boot.domain.Label;
import dev.omedia.boot.dto.ContactDTO;
import dev.omedia.boot.dto.NaturalPersonDTO;
import dev.omedia.boot.exception.BirthDateIsAfterNowException;
import dev.omedia.boot.exception.InvalidMailException;
import dev.omedia.boot.exception.InvalidMobileException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NaturalPersonValidation {
    private static int NUM_DIGITS_IN_MOBILE = 9;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean isValidMail(String mail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.matches();
    }

    public static void validateNaturalPerson(NaturalPersonDTO dto) throws BirthDateIsAfterNowException, InvalidMailException, InvalidMobileException {
        if (dto.getBirthDate().isAfter(LocalDate.now())) {
            throw new BirthDateIsAfterNowException("Natural person's birthdate should not be after current time");
        }

        dto.getContacts().forEach(
                contactDTO -> {
                    if (contactDTO.getLabel() == Label.MOBILE_NUM || contactDTO.getLabel() == Label.WORK_NUM
                            || contactDTO.getLabel() == Label.HOME_NUM) {
                        if (contactDTO.getValue().length() != NUM_DIGITS_IN_MOBILE) {
                            throw new InvalidMobileException(
                                    String.format("Mobile number can not have less/more than %d digits", NUM_DIGITS_IN_MOBILE));
                        }
                        for (char ch : contactDTO.getValue().toCharArray()) {
                            if (!Character.isDigit(ch)) {
                                throw new InvalidMobileException("Mobile number can not have anything but digits");
                            }
                        }
                    }else if (contactDTO.getLabel() == Label.MAIL || contactDTO.getLabel() == Label.WORK_MAIL) {
                        if (!isValidMail(contactDTO.getValue())) {
                            throw new InvalidMailException("Mail is in invalid format");
                        }
                    }
                }
        );
    }
}
