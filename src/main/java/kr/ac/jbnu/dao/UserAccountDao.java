package kr.ac.jbnu.dao;

import java.util.List;

import kr.ac.jbnu.model.UserAccount;

public interface UserAccountDao {
	public UserAccount findUser(String userEmail, String password);
	public UserAccount findUser(String userName);
	public boolean isBlockedUser(String userEmail);
	public void blockUserAccount(String userId);
	public void addUserAccount(UserAccount user);
	public List<UserAccount> queryUserAccount();
	public boolean checkUserAccount(String userId);
	public void updateUserAccount(UserAccount userAccount);
	void unblockUserAccount(String userId);
}
