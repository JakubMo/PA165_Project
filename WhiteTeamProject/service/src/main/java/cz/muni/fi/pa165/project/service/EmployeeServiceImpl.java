package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.EmployeeDao;
import cz.muni.fi.pa165.project.entity.Employee;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of {@link EmployeeService} interface. This class provides the implementation of the business logic.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 11/22/15.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void registerEmployee(Employee employee, String unencryptedPassword) throws DataAccessExceptionImpl {
		employee.setPasswordHash(generateStrongPasswordHash(unencryptedPassword));
		employeeDao.create(employee);
	}

	@Override
	public Employee get(Long id) {
		return employeeDao.get(id);
	}

	@Override
	public List<Employee> getAll() throws DataAccessExceptionImpl {
		return employeeDao.getAll();
	}

	@Override
	public Employee getByEmail(String email) throws DataAccessExceptionImpl {
		return employeeDao.getByEmail(email);
	}

	@Override
	public List<Employee> getAllByLastName(String lastName) throws DataAccessExceptionImpl {
		return employeeDao.getAllByLastName(lastName);
	}

	@Override
	public boolean authenticate(Employee employee, String password) {
		return validatePassword(password, employee.getPasswordHash());
	}

	private boolean validEmail(String email) {
		String EMAIL_PATTERN =
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private static String generateStrongPasswordHash(String password) {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		try {
			byte[] salt = getSalt().getBytes();

			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return iterations + ":" + toHex(salt) + ":" + toHex(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	private static boolean validatePassword(String originalPassword, String storedPassword) {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] hash = new byte[0];
		byte[] testHash = new byte[0];
		try {
			byte[] salt = fromHex(parts[1]);
			hash = fromHex(parts[2]);

			PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			testHash = skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}

		// Compares two byte arrays in length-constant time.
		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
