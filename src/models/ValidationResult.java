package models;

public class ValidationResult {

 public static enum Status {PASS, WARNING, ERROR}
 private String reason;
 private Status status;

 public ValidationResult(Status status)
 { this.status =status;

 }

 public String getReason() {
  return reason;
 }

 public void setReason(String reason) {
  this.reason = reason;
 }

 public Status getStatus() {
  return status;
 }

 public void setStatus(Status status) {
  this.status = status;
 }
}
