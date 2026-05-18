package com.tkart.ecommerce.services;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    private String buildHtmlEmail(String title, String subtitle, String otp, String warningText) {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                body {
                    font-family: 'Inter', Helvetica, Arial, sans-serif;
                    background-color: #f8fafc;
                    margin: 0;
                    padding: 0;
                    color: #334155;
                }
                .container {
                    max-width: 540px;
                    margin: 40px auto;
                    background: #ffffff;
                    border-radius: 16px;
                    box-shadow: 0 10px 25px -5px rgba(15, 23, 42, 0.05);
                    border: 1px solid #e2e8f0;
                    overflow: hidden;
                }
                .header {
                    background: linear-gradient(135deg, #2563eb, #1d4ed8);
                    color: #ffffff;
                    padding: 32px 24px;
                    text-align: center;
                }
                .header h1 {
                    margin: 0;
                    font-size: 28px;
                    font-weight: 700;
                    letter-spacing: -0.5px;
                }
                .content {
                    padding: 40px 32px;
                    text-align: center;
                }
                .title {
                    font-size: 22px;
                    font-weight: 600;
                    color: #0f172a;
                    margin-bottom: 12px;
                    margin-top: 0;
                }
                .subtitle {
                    font-size: 15px;
                    line-height: 1.6;
                    color: #64748b;
                    margin-bottom: 32px;
                }
                .otp-box {
                    background: #f1f5f9;
                    border: 2px dashed #cbd5e1;
                    border-radius: 12px;
                    padding: 20px;
                    margin: 0 auto 32px;
                    max-width: 320px;
                }
                .otp-code {
                    font-size: 36px;
                    font-weight: 700;
                    letter-spacing: 12px;
                    color: #0f172a;
                    margin: 0;
                    padding-left: 12px;
                }
                .warning {
                    font-size: 14px;
                    color: #94a3b8;
                    line-height: 1.5;
                    border-top: 1px solid #f1f5f9;
                    padding-top: 24px;
                    margin: 0;
                }
                .footer {
                    background: #f8fafc;
                    padding: 24px;
                    text-align: center;
                    font-size: 13px;
                    color: #94a3b8;
                    border-top: 1px solid #e2e8f0;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <h1>TKart Marketplace</h1>
                </div>
                <div class="content">
                    <h2 class="title">%s</h2>
                    <p class="subtitle">%s</p>
                    <div class="otp-box">
                        <div class="otp-code">%s</div>
                    </div>
                    <p class="warning">%s</p>
                </div>
                <div class="footer">
                    © 2026 TKart Ecommerce. All rights reserved.<br>
                    Secure Academic Marketplace
                </div>
            </div>
        </body>
        </html>
        """.formatted(title, subtitle, otp, warningText);
    }

    public void sendOtpEmail(String to, String otp) {
        log.info("========================================================");
        log.info("TKART DEV/LOCAL ENVIRONMENT - OTP GENERATED");
        log.info("Recipient: {}", to);
        log.info("OTP CODE: {}", otp);
        log.info("========================================================");

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("tkart-support@ecommerce.com");
            helper.setTo(to);
            helper.setSubject("TKart - Your Verification Code");

            String htmlContent = buildHtmlEmail(
                    "Verify Your Email Address",
                    "Thank you for starting your journey with TKart. Please use the verification code below to complete your registration.",
                    otp,
                    "This verification code will expire in 5 minutes. If you did not request this code, please safely ignore this email."
            );
            helper.setText(htmlContent, true);
            mailSender.send(message);
            log.info("OTP HTML email successfully sent to {}", to);
        } catch (Exception ex) {
            log.warn("SMTP Mail delivery failed for {}. Reason: {}. (You can use the OTP code logged above to verify!)", to, ex.getMessage());
        }
    }

    private String buildResetPasswordHtmlEmail(String title, String subtitle, String otp, String warningText) {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                body {
                    font-family: 'Inter', Helvetica, Arial, sans-serif;
                    background-color: #f8fafc;
                    margin: 0;
                    padding: 0;
                    color: #334155;
                }
                .container {
                    max-width: 540px;
                    margin: 40px auto;
                    background: #ffffff;
                    border-radius: 16px;
                    box-shadow: 0 10px 25px -5px rgba(220, 38, 38, 0.1);
                    border: 1px solid #fee2e2;
                    overflow: hidden;
                }
                .header {
                    background: linear-gradient(135deg, #dc2626, #991b1b);
                    color: #ffffff;
                    padding: 32px 24px;
                    text-align: center;
                }
                .header h1 {
                    margin: 0;
                    font-size: 28px;
                    font-weight: 700;
                    letter-spacing: -0.5px;
                }
                .content {
                    padding: 40px 32px;
                    text-align: center;
                }
                .title {
                    font-size: 22px;
                    font-weight: 600;
                    color: #991b1b;
                    margin-bottom: 12px;
                    margin-top: 0;
                }
                .subtitle {
                    font-size: 15px;
                    line-height: 1.6;
                    color: #64748b;
                    margin-bottom: 32px;
                }
                .otp-box {
                    background: #fff1f2;
                    border: 2px dashed #f43f5e;
                    border-radius: 12px;
                    padding: 20px;
                    margin: 0 auto 32px;
                    max-width: 320px;
                }
                .otp-code {
                    font-size: 36px;
                    font-weight: 700;
                    letter-spacing: 12px;
                    color: #881337;
                    margin: 0;
                    padding-left: 12px;
                }
                .warning {
                    font-size: 14px;
                    color: #ef4444;
                    line-height: 1.5;
                    border-top: 1px solid #fee2e2;
                    padding-top: 24px;
                    margin: 0;
                }
                .footer {
                    background: #f8fafc;
                    padding: 24px;
                    text-align: center;
                    font-size: 13px;
                    color: #94a3b8;
                    border-top: 1px solid #e2e8f0;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <h1>TKart Security Alert</h1>
                </div>
                <div class="content">
                    <h2 class="title">%s</h2>
                    <p class="subtitle">%s</p>
                    <div class="otp-box">
                        <div class="otp-code">%s</div>
                    </div>
                    <p class="warning">⚠️ <strong>Urgent Security Notice:</strong> %s</p>
                </div>
                <div class="footer">
                    © 2026 TKart Ecommerce. All rights reserved.<br>
                    Secure Academic Marketplace
                </div>
            </div>
        </body>
        </html>
        """.formatted(title, subtitle, otp, warningText);
    }

    public void sendResetPasswordEmail(String to, String otp) {
        log.info("========================================================");
        log.info("TKART DEV/LOCAL ENVIRONMENT - PASSWORD RESET OTP GENERATED");
        log.info("Recipient: {}", to);
        log.info("OTP CODE: {}", otp);
        log.info("========================================================");

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("tkart-support@ecommerce.com");
            helper.setTo(to);
            helper.setSubject("TKart - Password Reset Verification Code");

            String htmlContent = buildResetPasswordHtmlEmail(
                    "Password Reset Request",
                    "We received a request to reset your password for your TKart account. Please use the verification code below to reset your password.",
                    otp,
                    "This verification code will expire in 10 minutes. If you did not request a password reset, please safely ignore this email."
            );
            helper.setText(htmlContent, true);
            mailSender.send(message);
            log.info("Password reset HTML email successfully sent to {}", to);
        } catch (Exception ex) {
            log.warn("SMTP Mail delivery failed for {}. Reason: ex={}. (You can use the OTP code logged above to verify!)", to, ex.getMessage());
        }
    }
}
