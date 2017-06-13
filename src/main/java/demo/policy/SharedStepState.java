package demo.policy;

import java.util.HashMap;
import java.util.Map;

public class SharedStepState {

	private String jsonRequest;
	private String jsonResponse;
	private int responseCode;
	private String redeemTransID;
	private String Redemptionpoints;
	private String membershipMilesNo;
	private String cardMemberNumber;
	private Map<String, String> headers = new HashMap<String, String>();
	private String mrTierId;
	private String redeemTypeCode;
	private String redeemStatusCode;
	private String rewardPartnerCode;
	private String tripId;
	private String Cmrequest;
	//private ResponseStatus responseStatus;

	public String gettripId() {
		return tripId;
	}

	public void settripId(String tripId) {
		this.tripId = tripId;
	}

	public String getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(String jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	public String getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getmrTierId() {
		return mrTierId;
	}

	public void setmrTierId(String mrTierId) {
		this.mrTierId = mrTierId;
	}
	
	public String getredeemTypeCode() {
		return redeemTypeCode;
	}

	public void setredeemTypeCode(String redeemTypeCode) {
		this.redeemTypeCode = redeemTypeCode;
	}
	
	public String getredeemStatusCode() {
		return redeemStatusCode;
	}

	public void setredeemStatusCode(String redeemStatusCode) {
		this.redeemStatusCode = redeemStatusCode;
	}
	public String getQualificationCount() {
		return rewardPartnerCode;
	}

	public void setrewardPartnerCode(String rewardPartnerCode) {
		this.rewardPartnerCode = rewardPartnerCode;
	}

	public String getRedemptionpoints() {
		return Redemptionpoints;
	}

	public void setRedemptionpoints(String Redemptionpoints) {
		this.Redemptionpoints = Redemptionpoints;
	}
	
	
	public String getRedeemTransID() {
		return redeemTransID;
	}

	public void setredeemTransID(String redeemTransID) {
		this.redeemTransID = redeemTransID;
	}
	
	public String getcardMemberNumber() {
		return cardMemberNumber;
	}

	public void setcardMemberNumber(String cardMemberNumber) {
		this.cardMemberNumber = cardMemberNumber;
	}

/*	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}*/

	public String getmembershipMilesNo() {
		return membershipMilesNo;
	}

	public void setmembershipMilesNo(String membershipMilesNo) {
		this.membershipMilesNo = membershipMilesNo;
	}

	@Override
	public String toString() {
		return "SharedStepState [jsonRequest=" + jsonRequest + ", jsonResponse=" + jsonResponse + ", responseCode="
				+ responseCode + ", redeemTransID=" + redeemTransID + ", membershipMilesNo=" + membershipMilesNo
				+ ", cardMemberNumber=" + cardMemberNumber + ", headers=" + headers + ", mrTierId=" + mrTierId
				+ ", redeemTypeCode=" + redeemTypeCode + ",redeemStatusCode=" + redeemStatusCode + "rewardPartnerCode=" + rewardPartnerCode + "Redemptionpoints=" + Redemptionpoints + ", tripId=" + tripId + "]";
	}

}
